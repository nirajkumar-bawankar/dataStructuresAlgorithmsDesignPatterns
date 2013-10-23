#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "Place.h"

// can also use strlen or strcpy Standard Library C functions
// custom methods were only used to understand how they worked
static void copyString(char *target, char*source);
static int stringLength(char *string);


// On my honor:
//
// - I have not discussed the C language code in my program with
// anyone other than my instructor or the teaching assistants
// assigned to this course.
//
// - I have not used C language code obtained from another student,
// or any other unauthorized source, either modified or unmodified.
//
// - If any C language code or documentation used in my program
// was obtained from another source, such as a text book or course
// notes, that has been clearly noted with a proper citation in
// the comments of my program.
//
// Huanqing Liu

/**  Place_Set() initializes a new Place object.
 *   Pre:      pPlace points to a Place object
 *             FID has been initialized
 *             Name points to a zero-terminated ASCII string
 *             Lat points to a zero-terminated ASCII string
 *             Long points to a zero-terminated ASCII string
 *   Post:     pPlace->FID == FID
 *             pPlace->Name is a copy of Name (i.e., pPlace->Name != Name)
 *             pPlace->Latitude is a copy of Lat (i.e., pPlace->Latitude != Lat)
 *             pPlace->Longitude is a copy of Long (i.e., pPlace->Longitude != Long)
 *   Returns:  false if the object could not be properly initialized; true otherwise
 *  Called by: unknown client code that creates Place objects
 */bool Place_Set(Place* const pPlace, uint32_t FID, char* Name, char* Lat,
		char* Long) {
	if (stringLength(Name) > MAXNAMELENGTH || stringLength(Lat) > LATITUDELENGTH
			|| stringLength(Long) > LONGITUDELENGTH) {
		return false;
	}
	pPlace->FID = FID;
	pPlace->Name = malloc((stringLength(Name) + 1) * sizeof(char));
	pPlace->Latitude = malloc((stringLength(Lat) + 1) * sizeof(char));
	pPlace->Longitude = malloc((stringLength(Long) + 1) * sizeof(char));

	// check if malloc worked successfully
	if (pPlace->Name == NULL || pPlace->Latitude == NULL
			|| pPlace->Longitude == NULL ) {
		return false;
	} else {
		copyString(pPlace->Name, Name);
		copyString(pPlace->Latitude, Lat);
		copyString(pPlace->Longitude, Long);
		return true;
	}
}

/**  Place_Equals() indicates whether two Place objects have equal data members.
 *   Pre:      pLeft and pRight point to proper Place objects
 *   Post:     *pLeft and *pRight are unchanged
 *   Returns:  true if each member of *pLeft equals the corresponding member of *pRight;
 *             false otherwise
 *  Called by: PlaceQueue_Contains()
 */bool Place_Equals(const Place* const pLeft, const Place* const pRight) {
	if (pLeft->FID != pRight->FID) {
		return false;
	}

	int nameResult = strncmp(pLeft->Name, pRight->Name, stringLength(pLeft->Name));
	int latitudeResult = strncmp(pLeft->Latitude, pRight->Latitude,
			stringLength(pLeft->Latitude));
	int longitudeResult = strncmp(pLeft->Longitude, pRight->Longitude,
			stringLength(pLeft->Longitude));
	if (nameResult != 0 || latitudeResult != 0 || longitudeResult != 0) {
		return false;
	}
	return true;
}

void copyString(char *target, char *source) {
	while (*source != '\0') {
		*target = *source;
		source++;
		target++;
	}
	*target = '\0';
}

int stringLength(char *string) {
	int numberOfChars = 0;
	while (*string != '\0') {
		numberOfChars++;
		string++;
	}
	return numberOfChars;
}

