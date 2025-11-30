import java.util.Scanner;
import java.util.Random;

public class DiceGame {
    private static int bestScore = Integer.MAX_VALUE;
    private static boolean gameActive = true;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("=== ИГРА 'КИНУТЬ КОСТИ' ===");
        System.out.println("Компьютер загадал число от 1 до 100!");
        System.out.println("Попробуй угадать его!");
        System.out.println("Для просмотра статистики введи: RESULT");
        System.out.println("Для выхода из игры введи: EXIT");
        System.out.println("---------------------------");
        
        while (gameActive) {
            playGame(scanner, random);
            
            if (gameActive) {
                System.out.println("\nХочешь сыграть еще раз? (да/нет)");
                String playAgain = scanner.nextLine().trim().toLowerCase();
                
                if (!playAgain.equals("да") && !playAgain.equals("yes")) {
                    gameActive = false;
                    System.out.println("Спасибо за игру! До встречи!");
                }
            }
        }
        
        scanner.close();
    }
    
    private static void playGame(Scanner scanner, Random random) {
        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        boolean gameWon = false;
        
        System.out.println("\nНовая игра началась! Компьютер загадал число.");
        
        while (!gameWon && gameActive) {
            System.out.print("Введи число от 1 до 100: ");
            String input = scanner.nextLine().trim();
            
            // Обработка специальных команд
            if (input.equalsIgnoreCase("RESULT")) {
                showResults();
                continue;
            }
            
            if (input.equalsIgnoreCase("EXIT")) {
                gameActive = false;
                System.out.println("Выход из игры...");
                return;
            }
            
            // Проверка ввода числа
            try {
                int guess = Integer.parseInt(input);
                attempts++;
                
                if (guess < 1 || guess > 100) {
                    System.out.println("Пожалуйста, введи число от 1 до 100!");
                    continue;
                }
                
                if (guess == secretNumber) {
                    gameWon = true;
                    System.out.println("\n🎉 ПОЗДРАВЛЯЮ! Ты угадал число! 🎉");
                    System.out.println("Загаданное число: " + secretNumber);
                    System.out.println("Количество попыток: " + attempts);
                    
                    // Обновление лучшего результата
                    if (attempts < bestScore) {
                        bestScore = attempts;
                        System.out.println("🏆 Новый рекорд! 🏆");
                    }
                    
                } else if (guess < secretNumber) {
                    System.out.println("🎲 Я сам в шоке, но, загаданное число больше, брат");
                } else {
                    System.out.println("🎲 Не ожидал от тебя такого. Загаданное число меньше, брат");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введи корректное число или команду!");
            }
        }
    }
    
    private static void showResults() {
        System.out.println("\n=== СТАТИСТИКА ИГРЫ ===");
        if (bestScore == Integer.MAX_VALUE) {
            System.out.println("Лучшая игра: еще не сыграно");
        } else {
            System.out.println("Лучшая игра: " + bestScore + " попыток");
        }
        System.out.println("=======================");
    }
}