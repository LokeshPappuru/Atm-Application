import java.util.Scanner;
import java.lang.System;

interface exitStatus{
	default void status()
	{
		System.out.println("ThankYou.. Visit Again..");
	}
}
class WrongPinException extends Exception
{


}


class BalanceAmount{
	int balance = 100000;
	void checkBalance(){
		System.out.println("Fetching Your Balance...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("Your Balance : "+balance);
	}
	void withdrawAmount(int amount) {
		if(amount < balance)
		{
			balance = balance - amount;
		}
		else
		{
			System.out.println("Insufficient Funds");
			System.exit(0);
		}
	}
}


class Atm1 implements exitStatus{

	private int accountPin = 123;
	private int pin;
	void acceptInput()
	{
		Scanner sc = new Scanner(System.in);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		System.out.println("Enter your Pin Number");
		pin = sc.nextInt();
		sc.close();
	}
	void validatePin() throws Exception
	{
		System.out.println("Validating Your Pin...");
		if(accountPin == pin)
		{
			Scanner sc = new Scanner(System.in);
			BalanceAmount ba = new BalanceAmount();
			Thread.sleep(2000);
			System.out.println("Enter the option from below");
			System.out.println("1.Withdraw");
			System.out.println("2.CheckBalance");
			System.out.println("3.None");
			int option = sc.nextInt();
			if( option == 1)
			{
				Thread.sleep(3000);
				System.out.println("Enter the Amount");
				int amount = sc.nextInt();
				ba.withdrawAmount(amount);
				System.out.println("Transaction Processing....");
				Thread.sleep(3000);
				System.out.println("Collect Your Money - "+amount);
				Thread.sleep(3000);
				System.out.println("Enter the Option from Below");
				System.out.println("1.Check Balance");
				System.out.println("2.None");

				int option2 = sc.nextInt();
				if(option2 == 1 )
				{
					ba.checkBalance();
					status();
				}
				else
				{
					status();
					System.exit(0);
				}
				sc.close();
			}
			else if(option == 2)
			{
				Thread.sleep(2000);
				ba.checkBalance();
				status();
			}
			else
			{
				status();
				System.exit(0);

			}
		}
		else
		{
			Thread.sleep(2000);
			System.out.println("Invalid Pin Input");
			WrongPinException wpe = new WrongPinException();
			throw wpe;
		}
	}

}
class Bank1{
	void manage()
	{
		Atm1 a = new Atm1();
		try
		{
			a.acceptInput();
			a.validatePin();
		}
		catch(Exception e)
		{
			try
			{
				a.acceptInput();
				a.validatePin();
			}
			catch(Exception e1)
			{
				try
				{
					a.acceptInput();
					a.validatePin();
				}
				catch(Exception e2)
				{
					System.out.println("Card Blocked!!! Contact Your Bank!");
					System.exit(0);
				}
			}
		}
	}
}
class Rbi{
	public static void main(String[] args) {
		Bank1 b = new Bank1();
		System.out.println("Insert your Atm Card");
		b.manage();
	}
}
