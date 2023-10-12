class Account {
    private String name;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println(name + " attempts to withdraw $" + amount + ", but insufficient funds.");
        } else {
            balance -= amount;
            System.out.println(name + " successfully withdraws $" + amount + ". New balance: $" + balance);
        }
    }
}

class AccountOverdrawDemo {
    public static void main(String[] args) {
        Account jointAccount = new Account("Joint Account", 1000);

        Runnable withdrawRunnable = () -> {
            for (int i = 0; i < 5; i++) {
                jointAccount.withdraw(200);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread person1 = new Thread(withdrawRunnable, "Person 1");
        Thread person2 = new Thread(withdrawRunnable, "Person 2");

        person1.start();
        person2.start();
    }
}

class AccountOverdrawSafeDemo {
    public static void main(String[] args) {
        Account jointAccount = new Account("Joint Account", 1000);

        Runnable withdrawRunnable = () -> {
            for (int i = 0; i < 5; i++) {
                synchronized (jointAccount) {
                    jointAccount.withdraw(200);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread person1 = new Thread(withdrawRunnable, "Person 1");
        Thread person2 = new Thread(withdrawRunnable, "Person 2");

        person1.start();
        person2.start();
    }
}
