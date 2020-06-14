
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CoinChange {
	static int[] users_available_coin=coin_receiver();
	static int new_coin; //coin  the user wants to change
	public static void main(String []args){
		Scanner in= new Scanner(System.in);
	
		
		Integer[] avialable_coins =new Integer[]{1,2,5,10,20,50,100};
		System.out.println("enter the the the coin you wanted to change");
		 new_coin= in.nextInt();
		if(new_coin<0){
			System.out.println("coin can't be leass than 0");
		}
		conical_coin_changer(new_coin,avialable_coins);
		general_case_conical_coin_changer(new_coin);
		daynamic_conical_coin_changer(new_coin);


	}
	public static void conical_coin_changer(int new_coin,Integer[] avialable_coins){
		int no_of_avialable_coins;
		Collections.reverse(Arrays.asList(avialable_coins));
		for (int i : avialable_coins) {
			while(new_coin>=i){
				no_of_avialable_coins = Math.floorDiv(new_coin,i);
				new_coin = new_coin % i;
				System.out.print("("+no_of_avialable_coins+" X " +i+"$) ");
			}
		}

	}
	//to cheack  if the array contains this element
	public static boolean contains(int []arr, int toCheckValue) {
		for (int element : arr) {
		    if (element == toCheckValue) {
		        return true;
		    }
		}
		return false;
	}
	public static int[] coin_receiver(){
		System.out.println("enter number of avialable coin");
		Scanner in=new Scanner(System.in);
		int size=in.nextInt();
		int[] users_available_coin = new int[size];
		int coin;
		for(int i=0; i<size;i++){
			System.out.println("enter coin");
			coin=in.nextInt();
			if(coin<0){
				System.out.println("coin can't be less than 0  ");
				i--;
			}
			else if(contains(users_available_coin,coin)){
				System.out.println("coin already exists  ");
				i--;
			}
			else
				users_available_coin[i]=coin;
		}
		if(!contains(users_available_coin,1)){
			System.out.println("coin 1 must be in the con list enter again ");
			coin_receiver();
		}
		return users_available_coin;
	}

	public static void general_case_conical_coin_changer(int new_coin){
		System.out.println("\nin general case coins changer is: ");
		Arrays.sort(users_available_coin);
		int[] users_avialable_coins_inReverse=reverse(users_available_coin);
		int no_of_avialable_coins;
		for (int i : users_avialable_coins_inReverse){
			while(new_coin>=i){
				no_of_avialable_coins = Math.floorDiv(new_coin,i);
				new_coin = new_coin % i;
			System.out.print("("+no_of_avialable_coins+" X " +i+"$) ");
			}
		}

	}
	public static void daynamic_conical_coin_changer(int new_coin){
		Arrays.sort(users_available_coin);
		int [] coin_holder=new int[new_coin+1];
		Integer [] index_holder=new Integer[new_coin+1];
		for(int i=0;i<=new_coin;i++){
			coin_holder[i]=i;
			index_holder[i]=i;
		}
		
		int counter,coin,bestIndex;
		for(int current_change=0;current_change<=new_coin;current_change++){
			counter= current_change;
			for(int i=0;i<=users_available_coin.length-1;i++){
				coin= users_available_coin[i];
				if(current_change==0){
					coin_holder[current_change]=0;
					index_holder[current_change]=null;
					break;

				}
				else if( coin==current_change){
					coin_holder[current_change]=1;
					index_holder[current_change]=i;
					break;
				}
				else if(coin>current_change)
					break;
				else if(1+coin_holder[current_change-coin]<= counter){
					counter=1+coin_holder[current_change-coin];
					bestIndex=i;
					coin_holder[current_change]=counter;
					index_holder[current_change]=bestIndex;

				}

			}
		}
		
		System.out.println("\noptimal coin change is= ");
		int current_change = new_coin;
		ArrayList<Integer> optimalCoins =new ArrayList<Integer>();
		int coin2;

				while(current_change>0){
					coin2 = users_available_coin[index_holder[current_change]];
							optimalCoins.add(coin2);
					current_change = current_change - coin2;
				}
				
				System.out.println();
  				ArrayList<Integer> optimalCoinsList=(ArrayList<Integer>)optimalCoins.clone();
  				Set<Integer> set = new HashSet<>(optimalCoinsList);
  				optimalCoinsList.clear();
  				optimalCoinsList.addAll(set);

		for(int i=0;i<optimalCoinsList.size();i++){
			System.out.print("("+Collections.frequency(optimalCoins,optimalCoinsList.get(i))+" X "+optimalCoinsList.get(i)+"$) "); 
		}
}
	static int[] reverse(int[]validData) {
		for(int i = 0; i < validData.length / 2; i++)
		{
		    int temp = validData[i];
		    validData[i] = validData[validData.length - i - 1];
		    validData[validData.length - i - 1] = temp;
		}
		return validData;
	}
	}
		