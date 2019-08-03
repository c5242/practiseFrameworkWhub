package practise.practise1;

public class LargestandSecondlargest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int num[]={2,7,9,1,10,11,23,21,22};
		int large=0;
		int secLarge=0;
		
for(int i=0;i<num.length;i++)
{
if(num[i]>large)
{
	secLarge=large;//secLarge=11 ,21
	large=num[i];//large=23
}else if(num[i]>secLarge)//21>11,22>21
{
	secLarge=num[i];//22
	}
}
System.out.println("Largest is: "+large);
System.out.println("Second largest : "+secLarge);

	}

}
