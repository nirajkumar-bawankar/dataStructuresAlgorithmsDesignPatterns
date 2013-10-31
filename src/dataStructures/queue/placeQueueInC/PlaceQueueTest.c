/* Tests PlaceQueue.c method implementations */
#include <stdio.h>
#include <stdlib.h>
#include "PlaceQueue.h"

int main() {
	printf("\n-----------Test PlaceDT_Set------------\n");
	Place place1;
	Place_Set(&place1, 2, "Bei Jing", "123", "1234");

	PlaceDT placeDT;
	PlaceDT_Set(&placeDT, &place1);
	printf("placeDT.pItem->Name = Bei Jing = %s\n", placeDT.pItem->Name);
	if (placeDT.Node.next == NULL && placeDT.Node.prev == NULL ) {
		printf("placeDT.Node is correctly set\n");
	} else {
		printf("ERROR: placeDT->Node is incorrectly set\n");
	}

	printf("\n---------Test PlaceQueue_Init----------\n");
	PlaceQueue placeQueue;
	PlaceQueue_Init(&placeQueue);

	if (placeQueue.Q.fGuard.prev == NULL
			&& placeQueue.Q.fGuard.next == &(placeQueue.Q.rGuard)) {
		printf("PlaceQueue_Init() is correctly working\n");
	} else {
		printf("ERROR: PlaceQueue_Init() is incorrectly working\n");
	}

	if (placeQueue.Q.rGuard.next == NULL
			&& placeQueue.Q.rGuard.prev == &(placeQueue.Q.fGuard)) {
		printf("PlaceQueue_Init() is correctly working\n");
	} else {
		printf("ERROR: PlaceQueue_Init() is incorrectly working\n");
	}

	printf("\n--------Test PlaceQueue_Schedule-------\n");
	PlaceQueue placeQueue2;
	PlaceQueue_Init(&placeQueue2);

	Place place2;
	Place_Set(&place2, 2, "Bei Jing", "123", "1234");

	printf("adding place2...\n");
	PlaceQueue_Schedule(&placeQueue2, &place2);
	PlaceDT *placeDT2 =
			Queue_Entry(Queue_Front(&(placeQueue2.Q)), PlaceDT, Node);

	if (placeDT2->pItem == &place2) {
		printf("PlaceQueue_Schedule() is correctly working\n");
	} else {
		printf("ERROR: PlaceQueue_Schedule() is incorrectly working\n");
	}

	Place place3;
	Place_Set(&place3, 3, "Blacksburg", "123", "1234");

	printf("adding place3...\n");
	PlaceQueue_Schedule(&placeQueue2, &place3);
	PlaceDT *placeDT3 =
			Queue_Entry(Queue_Front(&(placeQueue2.Q))->next, PlaceDT, Node);

	if (placeDT3->pItem == &place3) {
		printf("PlaceQueue_Schedule() is correctly working\n");
	} else {
		printf("ERROR: PlaceQueue_Schedule() is incorrectly working\n");
	}

	printf("\n---------Test PlaceQueue_Visit---------\n");
	Place *removedPlace2 = PlaceQueue_Visit(&placeQueue2);

	if (removedPlace2 == &place2) {
		printf("PlaceQueue_Visit() is correctly working\n");
	} else {
		printf("ERROR: PlaceQueue_Visit() is incorrectly working\n");
	}

	Place *removedPlace3 = PlaceQueue_Visit(&placeQueue2);
	if (removedPlace3 == &place3) {
		printf("PlaceQueue_Visit() is correctly working\n");
	} else {
		printf("ERROR: PlaceQueue_Visit() is incorrectly working\n");
	}

	printf("\n--------Test PlaceQueue_Contains-------\n");
	PlaceQueue placeQueue3;
	PlaceQueue_Init(&placeQueue3);

	Place place4;
	Place_Set(&place4, 4, "Canada", "123", "1234");
	PlaceQueue_Schedule(&placeQueue3, &place4);
	printf("add place4 but not place5...\n");

	Place place5;
	Place_Set(&place5, 5, "China", "123", "1234");

	int result1 = PlaceQueue_Contains(&placeQueue3, &place4);
	int result2 = PlaceQueue_Contains(&placeQueue3, &place5);

	printf("PlaceQueue_Contains(&placeQueue3, &place4) = 1 = %d\n", result1);
	printf("PlaceQueue_Contains(&placeQueue3, &place5) = 0 = %d\n", result2);

	printf("\n---------Test PlaceQueue_Empty---------\n");
	PlaceQueue placeQueue4;
	PlaceQueue_Init(&placeQueue4);

	int result3 = PlaceQueue_Empty(&placeQueue4);

	Place place6;
	Place_Set(&place6, 6, "China", "123", "1234");
	PlaceQueue_Schedule(&placeQueue4, &place6);
	int result4 = PlaceQueue_Empty(&placeQueue4);
	printf("PlaceQueue_Empty(placeQueue4) = 1 = %d\n", result3);
	printf("add place6...\n");
	printf("PlaceQueue_Empty(placeQueue4)) = 0 = %d\n", result4);

	return 0;
}

