#include<iostream>
using namespace std;
int fn(int**t, int y, int x, int z)
{

	if (y == 1)
	{
		if (z == 1 || z == x - 1)
			return 1;
		else
		{
			return 0;
		}
	}

	else
	{
		int sum = 0;
		for (int i = 0; i < x; i++)
		{
			sum = sum + fn(t, y - 1, x, i)* t[i][z];
		}
		return sum;
	}
}
int main()
{
	int n, m;
	cin >> n >> m;
	int**p = new int*[n];
	for (int i = 0; i < n; i++)
	{
		p[i] = new int[n];
	}

	for (int i = 0; i < n; i++)
		for (int k = 0; k < n; k++)
		{
		
			if (k==(n-1+i)%n||k==(i+1)%n)
			{
				p[i][k] = 1;
			}
			else
			{
				p[i][k] = 0;
			}
		}
	cout << fn(p,m,n,0) << endl;
	system("pause");
}