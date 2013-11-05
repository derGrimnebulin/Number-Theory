#include <iostream>
#include <string>
#include <cstdlib>
#include <cmath>

using namespace std;


	int main()
	{
		int input;
		cout << "please enter your number:\n";
		cin >> input;
		const int MAX_SIZE = input;
		//Initialize the boolean array. Set all values except 0 and 1 equal to true.
		bool primeList[ MAX_SIZE ];

		for ( int i = 0 ; i < MAX_SIZE ; i++ )
		{
			if ( i<2 )
				primeList[i] = 0;
			else
			        primeList[i] = 1;
		}
		//Sieve of Eratoshenes.
		for (int i = 2 ; i <= int( sqrt( MAX_SIZE ) ) ; i++){
			if (primeList[i] == true)
			  {
			    int j = 0;
			    while (i*i+i*j <= MAX_SIZE)
			      {
			        primeList[i*i+i*j] = false;
			        j++;
			      }
			  }
		}

		for (int i = 0 ; i < MAX_SIZE ; i++)
		{
		    if (primeList[i]==true)
		      {
		        cout << i << "\n";
		      }
		}
		return 0;
	}



