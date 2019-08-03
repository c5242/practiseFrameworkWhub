package practise.practise1;

public class SmallestNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int num[]={2,7,9,1,10,11};
		int small=num[0];
		
for(int i=0;i<num.length;i++)
{
if(num[i]<small)
{
small=num[i];
System.out.println("Small is: "+small);
}
}
}

}
