public class SimuladorSemaforo {

    public static void main(String[] args){

        // crio a thread semaforo e instancio, quando eu faço isso automaticamente ele vai rodar o metodoo run
        ThreadSemaforo semaforo = new ThreadSemaforo();

        //pedi p imprimir 10x
        //aqui eu pedi pra imprimir a cor, porem a cor está como privado la no threadsemaforo
        //então pra facilitar eu criei um método get só pra imprimir a cor lá na classe threadSemaforo
        //aí eu coloco um semaforo.esperacormudar para depois passar para o proximo laço do for
        for (int i=0 ; i<10; i++) {

            System.out.println(semaforo.getCor());
            semaforo.esperaCorMudar();

        }

        //logo depois que terminar essas 10 passadas do semaforo eu pedi pra desligar o semaforo
        System.out.println("Processo finalizado");
        semaforo.deligarSemaforo();
    }
}
