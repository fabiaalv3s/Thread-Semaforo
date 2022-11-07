//aqui está nossa thread...
//dentro da thread crio um atributo cor
public class ThreadSemaforo implements Runnable{
    private CorSemaforo cor;
    private boolean parar;
    private boolean corMudou;


    public ThreadSemaforo(){
        //passei um a cor inicial e para poder executar eu passo a thread o metodo run
        // eu crio a classe this que passa instância da classe runable e dou um start.
        this.cor = CorSemaforo.VERMELHO;

        //this.parar = false;
        //this.corMudou = false;

        new Thread(this).start();

    }

    //no run fica mudando a cor do semaforo se eu não criasse uma flag ele iria ficar rodando direto sem parar
    //então eu criei um atributo do tipo boolean parar (boolean por padrao ele inicialmente é falso, mas se quiser pode
    // colocar )
    public void run(){
        //enquanto não pedir para parar, se não fica no looping infinito e se eu não colocar o while ele só vai
        //fazer uma troca de cor e eu quero determinar um limite de execução pra ele, então a eu peço pra ele esperar
        while (!parar) {
            try {
                //faço um switch e passo a cor, se for vermelho eu falo pra dormir um pouco
                //o metodo sleep pode disparar uma excessão então é por isso que eu crio um try catch
                switch (this.cor) {
                    case VERMELHO:
                        Thread.sleep(2000);
                        break;

                    case AMARELO:
                        Thread.sleep(300);
                        break;

                    case VERDE:
                        Thread.sleep(1000);
                        break;
                    default:
                        break;
                }

                // depois do switch peço pra ele Mudar a corK
                this.mudarCor();
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("processo interrompido");

            }
        }
    }

    //para mudar a cor faço um metodo privado, porque só vai ser utilizado dentro da propria classe
    //e como vamos fazer acesso a um atributo da propria classe e não correr o risco de uma outra thread tentar mudar
    //ao mesmo tempo eu coloco um syncronized
    private synchronized void mudarCor(){
        switch (this.cor) {
            //muda do vermelho pro verde
            case VERMELHO:
                this.cor = CorSemaforo.VERDE;
                break;
            //muda do amrelo pro vermelho
            case AMARELO:
                this.cor = CorSemaforo.VERMELHO;
                break;
            //muda do verde para amarelo
            case VERDE:
                this.cor = CorSemaforo.AMARELO;
                break;

            default:
                break;
        }
        //para ele rodar na ordem que eu quero vermelho, vamarelo e verde
        // usa o metodo notify pra notificar a thread que estiver esperando
        this.corMudou = true;
        notify();
    }

    //para isso eu tbm criei um metodo pra esperar mudar de cor
    //enquanto a cor não muda eu peço pra esperar
    public synchronized void  esperaCorMudar(){
        while (!this.corMudou) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //a cor mudou eu peço pra eperar
        this.corMudou = false;

    }

    //esse metodo get eu criei só pra imprimir a cor
    public CorSemaforo getCor() {
        return cor;
    }

    //peço pra deligar lkl
    public synchronized void deligarSemaforo() {
        this.parar= true;
    }
}

