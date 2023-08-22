import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class s40202642_detector {

	
	public static int min(int a,int b, int c)
	{
		int min= Math.min(a, b);
		min=Math.min(min, c);
		return min;
	}
	
	
	public static void run2(String st1, String st2)
	{
		st1=st1.replaceAll(" ", "");
		st2=st2.replaceAll(" ", "");
		
		
		
		int n=st1.length();
		int m=st2.length();
		
		int arr[][]= new int[n+1][m+1];
		
		if(n==0)
			System.out.println("0");
		else if(m==0)
			System.out.println("0");
		else
		{
			for(int i=0;i<n+1;i++)
			{
				arr[i][0]=i;
			}
			for(int i=0;i<m+1;i++)
			{
				arr[0][i]=i;
			}
			
			for(int i=1;i<n+1;i++)
			{
				for(int j=1;j<m+1;j++)
				{
					if(st1.charAt(i-1)==st2.charAt(j-1))
					{
						arr[i][j]= min((arr[i-1][j]), (arr[i][j-1]), (arr[i-1][j-1]));
					}
					else
					{
						arr[i][j]= min((arr[i-1][j]+1), (arr[i][j-1]+1), (arr[i-1][j-1]+1));
					}
				}
			}
			

			
	//		System.out.println(arr[n][m]);
			int max=Math.max(n, m);
			int plag=(max-arr[n][m])*100/max;
		//	System.out.println(max);
		//	System.out.println(plag);
			
			if(plag>25)
				System.out.println("1");
			else
				System.out.println("0");
			
		}
	}
	
	
	
	
	
	
	
	
	
	/// TEXT FILE
	static int res=0;
	public static String preProcess1(String s)
	{
		String st="";
		
		Pattern pattern =Pattern.compile("\"[^\\\"]*\"");
		Matcher matcher = pattern.matcher(s);
		
		while(matcher.find())
		{
			String str=matcher.group();
			str=str.replaceAll("\"", "");
			s=s+" "+str;
		}
	//	s=s.replaceAll("", "");
		
		
		return st;
	}
	
	public static String preProcess2(String s)
	{
		//s=s.replaceAll("\"[^\\\"]*\"", "");
		//	System.out.println(s);
			s = s.replaceAll("\\p{Punct}","");
			s=s.replaceAll("\n", "");
			s=s.replaceAll("is","");
			s=s.replaceAll("of", "");
			s=s.replaceAll("the", "");
			s=s.replaceAll("on", "");
			s=s.replaceAll(" a ", " ");
			s=s.replaceAll("to", "");
			s=s.replaceAll("that", "");
			s=s.replaceAll("for", "");
			s=s.replaceAll("it", "");
			s=s.replaceAll("\\d","");
			return s;
	}
	
	public static String preProcess3(String s)
	{
		s=s.replaceAll("\"[^\\\"]*\"", "");
		return s;
	}
	
	
	public static int check(String s, String t)
	{
		String[] st1=s.split(" ");
		String[] st2=t.split(" ");
		
		HashSet <String> set1 = new HashSet <String>();
	    HashSet <String> set2 = new HashSet <String>();

	    
	    for(int i=0;i<st1.length;i++)
	    {
	    	set1.add(st1[i]);
	    }
	    
	    for(int i=0;i<st2.length;i++)
	    {
	    	set2.add(st2[i]);
	    }
	   
	    Set<String> union = new HashSet<>();
	    
	    union.addAll(set1);
	    union.addAll(set2);
	    
	    set1.retainAll(set2);
	    
	  //  System.out.println(set1.size()*100/union.size());
	    res=res+set1.size()*100/union.size();
	    
	    return res;
		
	}
	
	
	public static void run1(String s, String t) throws IOException
	{
			
		// S1 contains quoted text
		//S2 contains non quoted text
		String s1=preProcess1(s);
		s1=preProcess2(s1);
		
		String s2=preProcess2(s);
		s2=preProcess3(s2);
		
		// T1 contains quoted text
		//T2 contains non quoted text
		String t1=preProcess1(t);
		t1=preProcess2(t1);
		
		String t2=preProcess2(t);
		t2=preProcess3(t2);
		
		int res1=check(s1,t2);
		int res2=check(s2,t1);
		int res3=check(s2,t2);
		
		int sum=res1+res2+res3;
		
		if(sum>40)
			System.out.println(1);
		else
			System.out.println(0);
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		String path1=args[0];
		String path2=args[1];
		
		
		
		FileReader fr1 = new FileReader(path1);
        BufferedReader br1 = new BufferedReader(fr1);
		String s="";
        String temp=null;
		while((temp=br1.readLine())!=null)
		{
			s=s+temp;
		}
//		System.out.println(s);
		
		
		FileReader fr2 = new FileReader(path2);
        BufferedReader br2 = new BufferedReader(fr2);
		String t="";
		while((temp=br2.readLine())!=null)
		{
			t=t+temp;
		}
//		Path fileName1= Path.of(path1);
//		Path fileName2= Path.of(path2);
//		
//		
//	
//		String s=Files.readString(fileName1);
//		String t=Files.readString(fileName2);
		
		if(s.contains("#include"))
		{
			//System.out.println("code");
			run2(s,t);
		}
		else
		{
			//System.out.println("text");
			run1(s,t);
		}
		
		
		
		
	
	}
	
	
}
