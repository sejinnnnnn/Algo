#include <iostream>
#include <stdio.h>


int arr[1000000] = { 0 };
int dp[1000000] = { 0 };

int main() {
	
	int T;
	scanf("%d", &T);
	
	long long remainder = 1000000009;
	
	for (int i = 0; i < T; i++) {
		scanf("%d", &arr[i]);
	}
	
	int biggest = 0;
	
	for (int i = 0; i < T; i++) {
		if (biggest < arr[i]) biggest = arr[i];
	}
	
	for (int i = 0; i < biggest; i++) {
		if (i == 0) dp[i] = 1;
		else if (i == 1) dp[i] = 2;
		else if (i == 2) dp[i] = 4;
		else {
			dp[i] = ((dp[i - 1] + dp[i - 2]) % remainder + dp[i - 3]) % remainder;
		}
	}
	
	for (int i = 0; i < T; i++) {
		printf("%d ", dp[arr[i] - 1]);
		
	}
	
	return 0;
}