import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
public class Main {

    public static void main(String[] args) throws IOException {
        CombSort combSort = new CombSort();
        FileReader file = new FileReader("/Users/macbook/Documents/IF_BBC/EAD/Estrutura_de_dados_II/Arquivos_ordenacao/dados_3.txt");
        BufferedReader readFile = new BufferedReader(file);
        FileWriter novoArquivo = new FileWriter("/Users/macbook/Documents/IF_BBC/EAD/Estrutura_de_dados_II/Arquivos_ordenacao/Ordenacao_1/dadosOrdenados_3.txt");
        PrintWriter gravarArquivo = new PrintWriter(novoArquivo);

        gravarArquivo.printf("Desenvolvedor: Clayton Rodrigues Dos Prazeres.\n ___________ \n");
        // preparando o documento

        double contador = 0.0;
        String line = "";
        long tempoInicial;
        long tempoFinal;

        String linha = readFile.readLine();
        while (linha != null) {
            linha = readFile.readLine();
            if (linha != null) {
                line = linha;
            }
        }
        file.close();
        line = line.replace("[", "");
        line = line.replace("]", "");
        line = line.replace(" ", "");
        String[] str = line.split(",");
        int[] dados = new int[str.length];
        iniciarVetor(dados,str);

        //InsertionSort
        tempoInicial = System.currentTimeMillis();
        contador = insertionSort(dados, contador);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("Insertion Sort Executado em = " + (tempoFinal) + " ms\nCom "+contador+" movimentos.\n");
        gravarArquivo.printf("\nInsertionSort: \n--------------------\n");
        gravarArquivo(gravarArquivo, contador, tempoFinal, dados);

        //SelectionSort
        contador = 0.0;
        iniciarVetor(dados,str);
        tempoInicial = System.currentTimeMillis();
        contador = selectionSort(dados, contador);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("SelectionSort Executado em = " + (tempoFinal) + " ms\nCom "+contador+" movimentos.\n");
        gravarArquivo.printf("\nSelectionSort: \n--------------------\n");
        gravarArquivo(gravarArquivo, contador, tempoFinal, dados);

        //BubbleSort
        contador = 0.0;
        iniciarVetor(dados,str);
        tempoInicial = System.currentTimeMillis();
        contador = bubbleSort(dados, contador);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("BubbleSort Executado em = " + (tempoFinal) + " ms\nCom "+contador+" movimentos.\n");
        gravarArquivo.printf("\nBubbleSort: \n--------------------\n");
        gravarArquivo(gravarArquivo, contador, tempoFinal, dados);

        //CombSort
        contador = 0.0;
        iniciarVetor(dados,str);
        tempoInicial = System.currentTimeMillis();
        contador = combSort.sort(dados, contador);
        tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("CombSort Executado em = " + (tempoFinal) + " ms\nCom "+contador+" movimentos.\n");
        gravarArquivo.printf("\nCombSort: \n--------------------\n");
        gravarArquivo(gravarArquivo, contador, tempoFinal, dados);


        gravarArquivo.close();
    }

    public static double insertionSort(int[] vetor, double insertionCont) {
        int j;
        int key;
        int i;

        for (j = 1; j < vetor.length; j++) {
            key = vetor[j];
            for (i = j - 1; (i >= 0) && (vetor[i] > key); i--) {
                vetor[i + 1] = vetor[i];
                insertionCont++;
            }
            vetor[i + 1] = key;
        }
        return insertionCont;
    }

    public static double selectionSort(int[] array, double selectionCont) {
        for (int fixo = 0; fixo < array.length - 1; fixo++) {
            int menor = fixo;
            for (int i = menor + 1; i < array.length; i++) {
                if (array[i] < array[menor]) {
                    menor = i;
                    selectionCont++;
                }
            }
            if (menor != fixo) {
                selectionCont++;
                int t = array[fixo];
                array[fixo] = array[menor];
                array[menor] = t;
            }
        }
        return selectionCont;
    }
    private static double bubbleSort(int vetor[], double bubbleCont){
        boolean troca = true;
        int aux;
        while (troca) {
            troca = false;
            for (int i = 0; i < vetor.length - 1; i++) {
                if (vetor[i] > vetor[i + 1]) {
                    aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                    bubbleCont++;
                    troca = true;
                }
            }
        }
        return bubbleCont;
    }
    public static void gravarArquivo(PrintWriter gravarArquivo, double contador, long tempoFinal, int dados[]){
        gravarArquivo.printf("O números de movimentos foi: "+contador+".\n");
        gravarArquivo.printf("O tempo gasto foi: "+tempoFinal+" ms.\n");
        gravarArquivo.printf("O vetor ordenado é: \n");
        for(int i = 0; i<dados.length; i++){
            if(i==0){
                gravarArquivo.printf("["+dados[i]+", ");
            }else if(i == dados.length-1){
                gravarArquivo.printf(dados[i]+"]");
            }else{
                gravarArquivo.printf(dados[i]+", ");
            }
        }
        gravarArquivo.printf("_______________________________\n\n\n");
    }
    public static void iniciarVetor(int dados[], String str[]){
        for (int i = 0; i < str.length; i++) {
            dados[i] = Integer.parseInt(str[i]);
        }
    }
}
