package Principal;

import javax.swing.JOptionPane;

public class ArvoreAnimais  {

	public ArvoreAnimais  noEsquerdo, noDireito;
	public String valor;
	
	public static ArvoreAnimais raiz;
	
	//Construtor da classe
	ArvoreAnimais(String value){
		this.valor=value;
	}

	public void inserirAnimalNaArvore(ArvoreAnimais noArvore) {

		String animal = JOptionPane.showInputDialog(null,
				"Qual o animal que você pensou?");

		String acao = JOptionPane.showInputDialog("Um(a) "
				+ animal + "______ mas um(a) " + noArvore.valor + " não.");

		//Substituir o campo "valor" do nó anterior com a ação do nome atual
		String substituicao = noArvore.valor;
		noArvore.valor = acao;
		
		//novo nó direito é o novo animal e o nó esquerdo é o antigo animal
		noArvore.noDireito = new ArvoreAnimais( animal);
		noArvore.noEsquerdo = new ArvoreAnimais(substituicao);
	}
	
	public void verificarAnimalNaArvore(ArvoreAnimais noArvore) {
		
		int resposta = JOptionPane.showConfirmDialog
				(null, "O animal que você pensou " + noArvore.valor, "Confirmar",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		//Verificará recursivamente até encontrar o animal caso ele esteja na arvore
		if (resposta == 0) {
			if (noArvore.noDireito == null)
				JOptionPane.showMessageDialog(null, "Acertei de novo!");
			else {
				verificarAnimalNaArvore(noArvore.noDireito);
			}
		} 
		
		// Se o animal não estiver na arvore, ele será inserido como folha
		if(resposta == 1) {
			if (noArvore.noEsquerdo == null)
				inserirAnimalNaArvore(noArvore);
			else {
				verificarAnimalNaArvore(noArvore.noEsquerdo);
			}
		}
	}

	public static void main(String[] args) {
		
		//inicialização da raiz da arovre com os valores padrões
		ArvoreAnimais arvoreAnimais = new ArvoreAnimais("vive na agua");
		raiz=arvoreAnimais;	
		raiz.noEsquerdo = new ArvoreAnimais("Macaco");
		raiz.noDireito = new ArvoreAnimais("Tubarao");
		
		int sair = 1;
		
		do {
			// Pergunta inicial
			JOptionPane.showMessageDialog(null, "Pense em um Animal");
			
			int resposta = JOptionPane.showConfirmDialog(null,
					"O animal que você pensou " + raiz.valor,"INICIO",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			// Se resposta for Sim, verificao nó direito, se for Não verifica o Esquerdo
			
			switch (resposta) {
			case 0:
				raiz.verificarAnimalNaArvore(raiz.noDireito);
				break;
			case 1:
				raiz.verificarAnimalNaArvore(raiz.noEsquerdo);
				break;

			case JOptionPane.CLOSED_OPTION:
				sair = 0;
				break;
			}
		} while (sair == 1);
	}
}
