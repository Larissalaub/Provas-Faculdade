import java.util.Scanner;

public class ProvaAlgoritimo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Declaração das variáveis
        String placa;
        int horaEntrada, minutoEntrada, horaSaida, minutoSaida;
        int horasEstacionado, minutosEstacionado;
        double valorTotal;

        // Solicitação e validação dos dados de entrada
        System.out.println("Informe a placa do veículo:");
        placa = scan.nextLine();

        System.out.println("Informe a hora de entrada (formato 24 horas):");
        horaEntrada = scan.nextInt();
        while (horaEntrada < 6 || horaEntrada > 22) {
            System.out.println("Horário de entrada inválido. O estacionamento funciona das 06:00 às 22:00. Informe novamente:");
            horaEntrada = scan.nextInt();
        }

        System.out.println("Informe o minuto de entrada:");
        minutoEntrada = scan.nextInt();
        while (minutoEntrada < 0 || minutoEntrada > 59) {
            System.out.println("Minuto de entrada inválido. Informe novamente:");
            minutoEntrada = scan.nextInt();
        }

        System.out.println("Informe a hora de saída (formato 24 horas):");
        horaSaida = scan.nextInt();
        while (horaSaida < 6 || horaSaida > 22 || horaSaida < horaEntrada) {
            System.out.println("Horário de saída inválido. O estacionamento funciona das 06:00 às 22:00 e a hora de saída deve ser posterior à hora de entrada. Informe novamente:");
            horaSaida = scan.nextInt();
        }

        System.out.println("Informe o minuto de saída:");
        minutoSaida = scan.nextInt();
        while (minutoSaida < 0 || minutoSaida > 59 || (horaSaida == horaEntrada && minutoSaida <= minutoEntrada)) {
            System.out.println("Minuto de saída inválido. Informe novamente:");
            minutoSaida = scan.nextInt();
        }

        // Cálculo do tempo estacionado
        horasEstacionado = horaSaida - horaEntrada;
        minutosEstacionado = minutoSaida - minutoEntrada;
        if (minutosEstacionado < 0) {
            horasEstacionado--;
            minutosEstacionado += 60;
        }

        // Verifica se o tempo estacionado é inferior a 10 minutos
        if (horasEstacionado == 0 && minutosEstacionado < 10) {
            System.out.println("O veículo permaneceu estacionado por menos de 10 minutos. Não há cobrança.");
            
        }

        // Limita o tempo máximo de cobrança a 5 horas
        if (horasEstacionado > 5) {
            horasEstacionado = 5;
            minutosEstacionado = 0;
        }

        // Cálculo do valor a ser pago
        valorTotal = horasEstacionado * 7.0 + minutosEstacionado * 7.0 / 60.0;
        if (horaSaida >= 6 && horaSaida <= 22) { // Verifica se o estacionamento foi dentro do horário de funcionamento
            if (valorTotal < 7.0) { // Valor mínimo de 1 hora
                valorTotal = 7.0;
            }
            if (horaEntrada >= 6 && horaEntrada <= 22) { // Verifica se o veículo estava estacionado durante o horário de funcionamento
                valorTotal = Math.min(valorTotal, 35.0); // Valor máximo de 5 horas
                System.out.println("Horário de funcionamento: 06:00 às 22:00");
            } else {
                System.out.println("Horário de entrada fora do horário de funcionamento. O veículo será cobrado pelo período de permanência até as 22:00.");
            }
            System.out.println("Valor a ser pago: R$" + valorTotal);
        } else {
            System.out.println("Horário de saída fora do horário de funcionamento. O veículo será cobrado pelo período de permanência até as 22:00.");
            System.out.println("Valor a ser pago: R$" + valorTotal);
        }

    
    }
}