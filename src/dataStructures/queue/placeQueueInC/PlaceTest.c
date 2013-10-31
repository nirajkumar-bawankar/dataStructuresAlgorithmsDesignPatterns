/* Tests Place.c method implementations */
#include <stdio.h>
#include <stdlib.h>
#include "Place.h"

// every c program needs a main function
/*
int main() {
	printf("\n-----------Test Place_Set-----------\n");
	Place place1;
	char Name[9] = "San Fran";
	char Latitude[7] = "123456";
	char Longitude[8] = "1234567";

	Place_Set(&place1, 42, Name, Latitude, Longitude);
	printf("place1.FID = 42 = %d\n", place1.FID);
	printf("place1.Latitude = 123456 = %s\n", place1.Latitude);
	printf("place1.Longitude = 1234567 = %s\n", place1.Longitude);

	Place_Set(&place1, 24, Name, "654321", "7654321");
	printf("place1.FID = 24 = %d\n", place1.FID);
	printf("place1.Latitude = 654321 = %s\n", place1.Latitude);
	printf("place1.Longitude = 7654321 = %s\n", place1.Longitude);

	printf("\n----------Test Place_Equals----------\n");
	Place place2;
	Place_Set(&place2, 2, "Bei Jing", "123", "1234");

	Place samePlace;
	Place_Set(&samePlace, 2, "Bei Jing", "123", "1234");

	Place placeWithDifferentFID;
	Place_Set(&placeWithDifferentFID, 3, "Bei Jing", "123", "1234");

	Place placeWithDifferentName;
	Place_Set(&placeWithDifferentName, 2, "New York", "123", "1234");

	Place placeWithDifferentLatitude;
	Place_Set(&placeWithDifferentLatitude, 2, "Bei Jing", "999", "1234");

	Place placeWithDifferentLongitude;
	Place_Set(&placeWithDifferentLongitude, 2, "Bei Jing", "123", "9999");

	printf("1 = %d\n", Place_Equals(&place2, &place2));
	printf("1 = %d\n", Place_Equals(&place2, &samePlace));
	printf("0 = %d\n", Place_Equals(&place2, &placeWithDifferentFID));
	printf("0 = %d\n", Place_Equals(&place2, &placeWithDifferentName));
	printf("0 = %d\n", Place_Equals(&place2, &placeWithDifferentLatitude));
	printf("0 = %d\n", Place_Equals(&place2, &placeWithDifferentLongitude));
	return 0;
}
*/
