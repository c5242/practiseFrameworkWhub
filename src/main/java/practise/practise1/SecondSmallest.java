package practise.practise1;

public class SecondSmallest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int num[]={2,7,9,1,10,11,23,21,22};
		int Small=num[0];
		int secSmall=num[0];
		
for(int i=0;i<num.length;i++)
{
if(num[i]<Small)
{
	secSmall=Small;//secLarge=11 ,21
	Small=num[i];//large=23
}else if(num[i]<secSmall)//21>11,22>21
{
	secSmall=num[i];//22
	}
}

System.out.println("Second Samllest : "+secSmall);

	}

}
