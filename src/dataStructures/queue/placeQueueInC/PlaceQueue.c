#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include "PlaceQueue.h"

/**  PlaceDT_Set() initializes a new PlaceDT object.
 *   Pre:      pPlaceDT points to a PlaceDT object
 *             pPlace points to a properly-initialized Place object
 *   Post:     pPlaceDT->pItem == pPlace
 *             The pointers in pPlaceDT->Node are NULL
 *   Returns:  false if the object could not be properly initialized;
 *             true otherwise
 *   Note:     Whether *pPlace was allocated dynamically or statically
 *             is unknown.  *pPlace is owned by the client who uses
 *             the PlaceQueue implementation, and deallocation of it
 *             is the responsibility of that client.
 *  Called by: PlaceQueue_Schedule()
 */bool PlaceDT_Set(PlaceDT* const pPlaceDT, Place* pPlace) {
	pPlaceDT->pItem = pPlace;
	QNode_Init(&(pPlaceDT->Node));
	return true;
}

/**  PlaceQueue_Init() initializes a new PlaceQueue object.
 *   Pre:      pPQ points to a PlaceQueue object
 *   Post:     pPQ->Q has been initialized to an empty state
 *   Returns:  false if the object could not be properly initialized; true otherwise
 *  Called by: unknown client code that uses a PlaceQueue object
 */bool PlaceQueue_Init(PlaceQueue* const pPQ) {
	Queue_Init(&(pPQ->Q));
	return true;
}

/**  PlaceQueue_Schedule() inserts a Place object into the queue.
 *   Pre:      pPQ points to a proper PlaceQueue object
 *             pPlace points to a properly-initialized Place object
 *   Post:     *pPlace has been inserted at the rear of the queue
 *   Returns:  false if the insertion could not be performed; true otherwise
 *  Called by: unknown client code that uses a PlaceQueue object
 */bool PlaceQueue_Schedule(PlaceQueue* const pPQ, Place* const pPlace) {
	PlaceDT *placeDT = malloc(sizeof(PlaceDT));

	if (!PlaceDT_Set(placeDT, pPlace)) {
		return false;
	} else {
		Queue_Push(&(pPQ->Q), &(placeDT->Node));
		return true;
	}
}

/**  PlaceQueue_Visit() pops the front element from the queue and returns it.
 *   Pre:      pPQ points to a proper PlaceQueue object
 *   Post:     pPQ points to a proper PlaceQueue object, with one less element
 *                 (unless the queue was empty)
 *   Returns:  pointer to the removed object; NULL if no object could be removed
 *  Called by: unknown client code that uses a PlaceQueue object
 *   Note:     PlaceDT objects are created and owned by a PlaceQueue object;
 *             therefore, it is the responsibility of the owning PlaceQueue
 *             object to properly deallocate them.
 */
Place* PlaceQueue_Visit(PlaceQueue* const pPQ) {
	QNode* removedNode = Queue_Pop(&(pPQ->Q));
	if (removedNode == NULL ) {
		return NULL ;
	}

	// Use macro within Queue class to retrieve the PlaceDT object
	// that contains the removedNode. Now the pItem field of the PlaceDT object
	// can be accessed within this method.
	PlaceDT *removedPlaceDT = Queue_Entry(removedNode, PlaceDT, Node);

	Place *place = removedPlaceDT->pItem;
	free(removedPlaceDT);
	return place;
}

/**  PlaceQueue_Contains() indicates whether the queue contains a specific
 *   Place object.
 *   Pre:      pPQ points to a proper PlaceQueue object
 *             pPlace points to a proper Place object
 *   Post:     *pPQ and *pPlace are unchanged
 *   Returns:  true if the queue contains a Place object that matches
 *             *pPlace, according to Place_Equals();
 *             false otherwise
 *  Called by: unknown client code that uses a PlaceQueue object
 */bool PlaceQueue_Contains(const PlaceQueue* const pPQ,
		const Place* const pPlace) {
	if (PlaceQueue_Empty(pPQ)) {
		return false;
	}
	QNode* pointerToQueueFrontNode = Queue_Front(&(pPQ->Q));

	// iterate through the PlaceQueue
	while (pointerToQueueFrontNode != Queue_End(&(pPQ->Q))) {
		PlaceDT *correspondingPlaceDT =
				Queue_Entry(pointerToQueueFrontNode, PlaceDT, Node);
		Place *correspondingPlace = correspondingPlaceDT->pItem;

		if (Place_Equals(correspondingPlace, pPlace)) {
			return true;
		}
		pointerToQueueFrontNode = pointerToQueueFrontNode->next;
	}
	return false;
}

/**  PlaceQueue_Empty() indicates whether the queue contains elements.
 *   Pre:      pPQ points to a proper PlaceQueue object
 *   Post:     pPQ points to an unchanged PlaceQueue object
 *   Returns:  true iff pPQ->Q is empty
 *  Called by: unknown client code that uses a PlaceQueue object
 *   Note:     PlaceDT objects are created and owned by a PlaceQueue object;
 *             therefore, it is the responsibility of the owning PlaceQueue
 *             object to properly deallocate them.
 */bool PlaceQueue_Empty(const PlaceQueue* const pPQ) {
	return Queue_Empty(&(pPQ->Q));
}
