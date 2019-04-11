package supermercado;

import java.util.Scanner;

/**
 * Criado em 16/09/2018 20:51.
 */
public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        //OBS.: Tudo foi passado para 'ProcedimentosGerais'

        //Carregar memória inicial
        Memoria.CarregarMemoria();

        //Carregar procedimento principal
        ProcedimentosGerais.MudarTipo();

        //Fechar scanner
        scan.close();
    }
}

/*//MAIN ANTIGO
public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){

        //Carregar memória inicial
        Memoria.CarregarMemoria();

        //Variavel para resposta
        String resp = "";

        //Criar um funcionário
        Funcionario funcionario = new Funcionario();
        System.out.println("Digite o nome do funcionário: ");
        String nome = scan.nextLine();
        funcionario.setNome(nome);

        //Criar um cargo
        Cargo cargo = new Cargo();
        cargo.setDescricao("Operador de caixa");
        funcionario.setCargo(cargo);

        //Criar novo cliente
        Cliente cliente = new Cliente();
        //Perguntar sobre o cpf na nota
        while(!resp.equals("S") && !resp.equals("s") && !resp.equals("N") && !resp.equals("n")){
            limparTela();
            System.out.println("Deseja colocar o CPF na nota? (s/n)");
            resp = scan.nextLine();
        }
        //Verificar escoha
        if(resp.toUpperCase().equals("S")){
            //Validar cpf do cliente
            String cpf = "";
            while(cpf.equals("")) {
                System.out.println("Informe o CPF do cliente: ");
                try {
                    cpf = Cliente.ValidarCPF(scan.nextLine());
                }catch(Exception e){
                    System.out.println("CPF inválido!");
                }
                if(cpf.equals("")) System.out.println("CPF inválido!");
            }
            cliente.setCpf(cpf);
        }

        //Iniciar as compras
        System.out.println("Vamos as compras!!");
        //Condicional de parada
        boolean continuar = true;
        do {
            limparTela();
            //Definir categoria selecionada como -1
            int categCount = -1;
            //Enquanto a categoria for <= 0 ou fora do limite
            while(categCount <= 0 || categCount > Memoria.getProdCateList().size()) {
                //Contagem das categorias
                int count  = 0;
                //Para cada categoria na lista
                for (String categ : Memoria.getProdCateList()) {
                    count++;
                    //Imprima o numero da ctegoria e sua descrição
                    System.out.printf("(%2d)    %s\n", count, categ);
                }
                //Escolher a categoria
                System.out.println("Informe a catégoria do produto a ser comprado: ");
                //Validar escolha
                try {
                    categCount = Integer.parseInt(scan.nextLine());
                }catch(Exception e){
                    System.out.println("Categoria inválida!");
                }
                //Eliminar escolha fora dos limites
                if(categCount <= 0 || categCount > Memoria.getProdCateList().size()) System.out.println("Categoria inválida!");
            }

            //Definir produto selecionado como -1
            int prodCount = -1;
            //Definir produto de retorno como null
            Produto prod = null;
            //Enquanto o produto selecionado estiver fora dos limites ou fora da categoria
            while(prodCount <= 0 || prodCount > Memoria.getProdList().size() || !prod.getCategoria().equals(Memoria.getProdCateList().get(categCount-1))){
                //Informe a categoria selecionada
                System.out.println("Categoria selecionada: '" + Memoria.getProdCateList().get(categCount-1) + "'");
                //Para cada produto na lista
                for(Produto produto : Memoria.getProdList()){
                    //Se pertencer a categoria, imprima o código, descrição e valor
                    if(produto.getCategoria().equals(Memoria.getProdCateList().get(categCount-1))) System.out.printf("(%3d)    %-40sR$ %.2f\n",produto.getCod(),produto.getDescricao(),produto.getValor());
                }
                System.out.println("Informe o produto a ser comprado: ");
                //Validar escolha do produto
                try{
                    prodCount = Integer.parseInt(scan.nextLine());
                }catch (Exception e){
                    System.out.println("Produto inválido!");
                }
                //Definir o produto escolhido
                prod = Memoria.getProdList().get(prodCount-1);
                //Verificar validade do produto escolhido
                if(prodCount <= 0 || prodCount > Memoria.getProdList().size() || !prod.getCategoria().equals(Memoria.getProdCateList().get(categCount-1))) System.out.println("Produto inválido!");
            }

            //Definir a quantidade do produto como -1
            int quantCount = -1;
            //Enquanto a quantidade for -1
            while(quantCount <= 0){
                //Informe o produto escolhido
                System.out.printf("Produto escolhido: (%3d)    %-40sR$ %.2f\n",prod.getCod(),prod.getDescricao(),prod.getValor());
                System.out.println("Informe a quantidade desse produto a ser comprada: ");
                //Valide a quantidade informada
                try{
                    quantCount = Integer.parseInt(scan.nextLine());
                }catch (Exception e){
                    System.out.println("Quantidade inválida!");
                }
                if(quantCount <= 0) System.out.println("Quantidade inválida!");
            }
            //Se tudo der certo, adicione o produto no "carrinho de compras" do cliente
            cliente.AdicionarProdClie(prod, quantCount);

            //Definir a resposta como "nada"
            resp = "";
            //Enquanto a resposta for diferente de um 'S' ou 'N'
            while(!resp.equals("S") && !resp.equals("s") && !resp.equals("N") && !resp.equals("n")){
                //Pesgunte se deve continuar
                System.out.println("Deseja escolher mais itens? (s/n)");
                resp = scan.nextLine();
            }
            if(resp.toUpperCase().equals("S")){
                continuar = true;
            }else{
                continuar = false;
            }
        } while(continuar);

        //Se a lista estiver concluida
        limparTela();
        //Imprima a nota fiscal da compra
        System.out.println("------------------------------------------------------");
        System.out.println("Supermecardo FAT");
        System.out.println("Operador: " + funcionario.getNome());
        System.out.println("CPF do cliente: " + cliente.getCpf());
        System.out.println("------------------------------------------------------");
        System.out.println("------------------------------------------------------");
        double valorTotal = 0;
        for(ProdutoCliente prodClie : cliente.getProdClieList()){
            //System.out.println(prodClie.getProduto().getDescricao() + " " + prodClie.getQuantidade() + " x R$ " + prodClie.getProduto().getValor());
            System.out.printf("%-25s %s %4d %-5s %.2f\n",prodClie.getProduto().getDescricao()," ",prodClie.getQuantidade()," x R$ ",prodClie.getProduto().getValor());
            valorTotal += prodClie.getProduto().getValor() * prodClie.getQuantidade();
        }
        //System.out.println("\n\nValor total da compra: R$ " + valorTotal);
        System.out.printf("\n\nValor total da compra: %-5s %.2f\n","R$",valorTotal);
        System.out.println("------------------------------------------------------");
        scan.close();
    }

    private static void limparTela(){
        for(int i = 0; i<50; i++){
            System.out.println();
        }
    }
}
*/


/*//SIMULAR COMPRA DE UM PRODUTO ESPECÍFICO
int c = -1;
while(c <= 0 || c > Memoria.getProdCateList().size()) {
    System.out.println("Simular compra TESTE-01:");
    int count  = 0;
    for (String categ : Memoria.getProdCateList()) {
        count++;
        System.out.printf("(%2d)    %s\n", count, categ);
    }
    System.out.println("Informe a catégoria do produto a ser comprado: ");
    try {
        c = Integer.parseInt(scan.nextLine());
    }catch(Exception e){
        System.out.println("Categoria inválida!");
    }
    if(c <= 0 || c > Memoria.getProdCateList().size()) System.out.println("Categoria inválida!");
}
int p = -1;
Produto prod = null;
while(p <= 0 || p > Memoria.getProdList().size() || !prod.getCategoria().equals(Memoria.getProdCateList().get(c-1))){
    System.out.println("Categoria selecionada: '" + Memoria.getProdCateList().get(c-1) + "'");
    for(Produto produto : Memoria.getProdList()){
        if(produto.getCategoria().equals(Memoria.getProdCateList().get(c-1))) System.out.printf("(%3d)    %-40sR$ %.2f\n",produto.getCod(),produto.getDescricao(),produto.getValor());
    }
    System.out.println("Informe o produto a ser comprado: ");
    try{
        p = Integer.parseInt(scan.nextLine());
    }catch (Exception e){
        System.out.println("Produto inválido!");
    }
    prod = Memoria.getProdList().get(p-1);
    if(p <= 0 || p > Memoria.getProdList().size() || !prod.getCategoria().equals(Memoria.getProdCateList().get(c-1))) System.out.println("Produto inválido!");
}
int q = -1;
while(q <= 0){
    System.out.printf("Produto escolhido: (%3d)    %-40sR$ %.2f\n",prod.getCod(),prod.getDescricao(),prod.getValor());
    System.out.println("Informe a quantidade desse produto a ser comprada: ");

    try{
        q = Integer.parseInt(scan.nextLine());
    }catch (Exception e){
        System.out.println("Quantidade inválida!");
    }
    if(q <= 0) System.out.println("Quantidade inválida!");
}
System.out.printf("Compra realizada!\nProduto escolhido: (%3d)    %-40sR$ %.2f\nQuantidade escolhida: %d\nValor total: R$ %.2f",
        prod.getCod(),prod.getDescricao(),prod.getValor(),q,q*prod.getValor());
*/

/*for(Produto p : prodLista){
    if(p.getCategoria().equals(prodCateLista.get(0))){
        System.out.println("Produto " + p.getCod() + "/" + p.getCodC() + ": " + p.getDescricao());
    }
}*/