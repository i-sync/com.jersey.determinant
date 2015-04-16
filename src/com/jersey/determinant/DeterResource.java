package com.jersey.determinant;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/deter")
public class DeterResource {
	
	@GET
	@Path("/hello")
	@Produces({MediaType.TEXT_PLAIN})
	public String sayHello()
	{
		return "hello determinant";
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Determinant getDeter()
	{
		Determinant deter = new Determinant();
		deter.setOrder(2);
		double [][] array = new double[2][2];
		array[0][0]=1;
		array[0][1]=2;
		array[1][0]=3;
		array[1][1]=4;
		deter.setArray(array);
		return deter;
	}
	
	@POST
	@Path("/calc")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Determinant calcDeter(Determinant determinant)
	{
		double value = deter(determinant.getArray());
		determinant.setValue(value);
		return determinant;
	}
	
	/*
	 * calculate the determinant value
	 */
	public double deter(double[][] array)
	{
		int flag =1;
		double sum=1;
		int len = array.length;
		double temp;
		for(int i=0,j;i<len-1;i++)
		{
			j=i;
			if(array[i][j]==0)
			{
				boolean b=false;
				for(int k=i+1;k<len;k++)
				{
					if(array[k][j]!=0)//找到一行不为0的,然后换行 
					{
						for(int l=j;l<len;l++)
						{
							temp=array[k][l];
							array[k][l]=array[i][l];
							array[i][l]=temp;
						}
						flag*=-1;
						b=true;
						break;
					}
				}
				if(!b)
				{
					return 0;
				}
				i--;
				continue;
			}
			for(;j<len-1;j++)
			{
				if(array[j+1][i]==0)continue;
				temp = -array[j+1][i]/array[i][i];
				for(int k=i;k<len;k++)
					array[j+1][k]+=array[i][k]*temp;
			}
		}
		
		for(int i=0;i<len;i++)
			sum*=array[i][i];
		return sum*flag;
	}
}
