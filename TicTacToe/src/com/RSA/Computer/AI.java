/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RSA.Computer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sabuz
 */


public class AI
{
     char play[][];

   public AI()
   {
       play = new char[5][5];
	for(int i=0;i<3;i++)
          for(int j=0;j<3;j++)
	     play[i][j]='B';
   }


    public boolean isWin(char ch)
    {
	int i,j,k;

	 for(i=0;i<3;i++)
	 {
	      k=0;
	    for(j=0;j<3;j++)
	    {
	       if(play[i][j]!=ch)
	          break;
	       k=k+1;
	    }

	      if(k==3)
	        return true;
	  }

	   for(i=0;i<3;i++)
	   {
	       k=0;
	      for(j=0;j<3;j++)
	      {
		   if(play[j][i]!=ch)
		      break;
	             k=k+1;
	      }

		   if(k==3)
		     return true;
	   }

	        k=0;
	   for(i=0;i<3;i++)
	   {
	      if(play[i][i]!=ch)
	        break;
	      k=k+1;
	  }

	     if(k==3)
	       return true;



	     k=0;
	 for(i=0,j=3-1; i<3; i++,j--)
	 {
	    if(play[i][j]!=ch)
	       break;
	      k=k+1;
		  }

	   if(k==3)
	     return true;

		  return false;

	}




	private List<Index> getPossibleMoves()
	{
		List<Index> bc = new ArrayList<>();

		    if(isWin('X')==true || isWin('O')==true)
		        return bc;

		  for(int i=0;i<3;i++)
		    for(int j=0;j<3;j++)
		       if(play[i][j]=='B')
		         {
                            Index bn = new Index();
			    bn.setRow(i);
			    bn.setColumn(j);
			    bc.add(bn);
			 }

		return bc;
	}

	private int getIndivialScore(char a[])
	{
		  int i,score=0;

		for(i=0;i<a.length;i++)
		{
			  if(a[i]=='O')
			  {
				  if(score < 0)
				     return 0;
				  else if(score==0)
				    score = 1;
				  else if(score > 0)
				     score = score*10;
			  }
			  else if(a[i]=='X')
			  {
				  if(score>0)
				     return 0;
				  else if(score==0)
				    score = -1;
				  else if(score<0)
				    score = score*10;
			  }
		}


			return score;
	}

	public int getScore()
	{
		  int i,j,res=0;
		  char board[];

		for(i=0;i<3;i++)
		{
			board = new char[5];
		  for(j=0;j<3;j++)
		     board[j] = play[i][j];

		     res = res + getIndivialScore(board);
		}


		 for(i=0;i<3;i++)
		 {
			   board = new char[5];
			for(j=0;j<3;j++)
			    board[j] = play[j][i];

			  res = res + getIndivialScore(board);
		 }

		    board = new char[5];
		 for(i=0;i<3;i++)
			 board[i] = play[i][i];

		res = res + getIndivialScore(board);


		       board = new char[5];
		 for(i=0, j=3-1; i<3; i++, j--)
		     board[i] = play[i][j];

		  res = res + getIndivialScore(board);

		return res;
	}


	public int[] minmax(int depth, char ch, int alpha, int beta)
	{
		   List<Index> bc = new ArrayList<>();
		   Index bn = new Index();

		     bc = getPossibleMoves();

		     int i,xx,xy,score,row=-1,column=-1;
		       if(bc.size() == 0 || depth == 0)
		       {
                          score = getScore();
			  return new int[] {score,row,column};
	               }


			   for(i=0;i<bc.size();i++)
			   {
			       bn = bc.get(i);
			       xx = bn.getRow();
			       xy = bn.getColumn();
			       play[xx][xy] = ch;

			        if(ch == 'O')
			        {
			            score = minmax(depth-1, 'X', alpha, beta)[0];

				     if(score > alpha)
				     {
					 alpha = score;
					 row = xx;
					 column = xy;
				     }
				}
				
                                else
				{
				    score = minmax(depth-1, 'O', alpha, beta)[0];

				     if(score < beta)
				     {
					beta = score;
					row = xx;
					column = xy;
	         		     }
				 }

				   play[xx][xy] = 'B';

				     if(alpha >= beta)
				         break;
			    }

			     score = beta;
			       if(ch=='O')
			        score = alpha;

          return new int[] {score,row,column};

	}


	public void setPlayer(char ch, int r, int c)
	{
	     play[r][c]=ch;
	}


}

