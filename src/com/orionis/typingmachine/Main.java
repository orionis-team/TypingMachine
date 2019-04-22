/**
 *   Package principal da aplicação
 */

package com.orionis.typingmachine;

//Importando todos os utils do java para usar durante o código.
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*; 


/**
 * @author Orionis Team
 *    Para fins de aprendizado!
 */

class Main { 

	
	/*
	 *  Função para converter a "String" em uma lista de caracteres
	 */
	public static List<Character> 
	convertStringToCharList(String textoparadigitar) throws AWTException, InterruptedException 
	{ 
		
		// Cria uma lista vazia de caracteres
		List<Character> caracteres = new ArrayList<>(); 

		/* For cada caractere na "String"
		 *  add na lista...
		 */
		for (char letra : textoparadigitar.toCharArray()) { 
            
			caracteres.add(letra); 
			
	        // Configure aqui o tempo para digitar cada letra, Obviamente em milisegundos.
			Thread.sleep(100);
			
			// Converte o char "letra" na string "copiar"
			String copiar = Character.toString(letra); 
			
			// Prepara nossa string "copiar" para ser copiada para o clipboard.
			StringSelection selection = new StringSelection(copiar);
			
			//Cria o robô que será usado para simular ações no teclado.
			Robot robot = new Robot();
			
	        // Detecta os espaços na string e simula o espaço pressionado.
			boolean espaco = Character.isWhitespace(letra); 
			if ( espaco == true ) {
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            System.out.println("[!] Espaço detectado.\n");
			}

			
			/*
			 * Se não tiver espaços na string ele
			 * copia para o clipboard e simula um CTRL + V
			 */
		
			if( espaco == false) {
			// Copia a letra para o clipboard...
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
			
			// Aqui o CTRL + V é simulado.
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
			}
			
		} 

		// retorna nossa lista de caracteres.
		return caracteres; 
	} 

	// Código inicial
	public static void main(String[] args) throws AWTException, InterruptedException 
	{ 
		
		// Aqui o script é iniciado e pausado por 1000MS
		System.out.println( "[+] Iniciando Script...\n" );
		Thread.sleep(1000);
		
        Scanner in = new Scanner(System.in);

		// Nosso texto para ser convertido
		String textoparadigitar; 
		
		// Faz a pergunta do texto para ser digitado
        System.out.print( "[?] Digite o texto para ser redigitado: " );
        
        // Transforma a input do usuário em uma string que será dividida e processada.
        textoparadigitar = in.nextLine();
        
        System.out.println( "[!] Certo! este texto será digitado após 5 segundos.\n" );
		System.out.println( "[>] Clique no local para ser digitado neste tempo!\n" );
        
		/*
		 *  Em 4 segundos ele começa a processar a string
		 *  e rodar tudo... ( eu sei que disse 5 mas 5 segundos 
		 *  parece demorar mais do que realmente 5 segundos,
		 *  4 segundos em java parece mais 5 segundos na vida real... )
		 */
        Thread.sleep(4000);
        
		// Pega nossa lista de caracteres e começa a função que irá processar isso.
		List<Character> 
			caracteres = convertStringToCharList(textoparadigitar); 
		

	} 
} 

