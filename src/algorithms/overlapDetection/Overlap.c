/**
 * @author Quinn Liu (quinnliu@vt.edu)
 */
#include <stdio.h>      // for I/O functions
#include <stdlib.h>     // generally useful
#include <stdbool.h>    // for the bool type
#include <stdint.h>     // for exact-width integer types
#include <inttypes.h>   // for exact-width integer output

#define MAXLEN 100

// Declaration for function Overlap():
bool Overlap(int32_t aSWx, int32_t aSWy, uint32_t aHeight, uint32_t aWidth,
             int32_t bSWx, int32_t bSWy, uint32_t bHeight, uint32_t bWidth);
			 
int main( ) 
{

   FILE *Data = fopen("TestData.txt", "r+");         // 1: Open the test data file
   FILE *Log  = fopen("Results.txt", "w+");         // 2: Open the results log file

   int32_t aSWx, aSWy, aHeight, aWidth, // specification of 1st rectangle
           bSWx, bSWy, bHeight, bWidth; // specification of 2nd rectangle

   char Line[MAXLEN + 1];
   fgets(Line, MAXLEN, Data);      // Read header lines
   fgets(Line, MAXLEN, Data);

   fprintf(Log, "Rectangle A                         Rectangle B\n");  // 3: Write data header lines to the log file
   fprintf(Log, "SW corner       Height    Width     SW corner       Height    Width\n");
   fprintf(Log, "-------------------------------------------------------------------\n");

   // 4: Read the data for first pair of rectangles:
   int32_t numRead = fscanf(Data, "%d%d%d%d%d%d%d%d", &aSWx, &aSWy, &aHeight, &aWidth, &bSWx, &bSWy, &bHeight, &bWidth);
   
   while ( numRead == 8 ) 
   { // 5: Check if all values were read

      // 6: Write data for current rectangles to log:
      fprintf(Log, "(%5d,%6d)%8d%9d     (%5d,%6d)%8d%9d\n", aSWx, aSWy, aHeight, aWidth, bSWx, bSWy, bHeight,
                                                            bWidth); 

      // 7: Test for an overlap and log the results:
      if (Overlap(aSWx, aSWy, aHeight, aWidth, bSWx, bSWy, bHeight, bWidth)) 
      {
         fprintf(Log, "     overlap detected\n");
      }
      else 
      {
         fprintf(Log, "     no overlap detected\n");
      }
      
      // 8: Read data for next pair of rectangles:
      numRead = fscanf(Data, "%d%d%d%d%d%d%d%d", &aSWx, &aSWy, &aHeight, &aWidth, &bSWx, &bSWy, &bHeight, &bWidth ); 
   }

   // 9: Write the marker for the end of the table:
   fprintf(Log, "-------------------------------------------------------------------\n"); 

   fclose(Data);    // 10: Close the input and output files
   fclose(Log);
   return 0;
}

/**  Determines whether two rectangles, A and B, intersect.
 *
 *   Pre:
 *         aSWx and aSWy specify the SW (lower, left) corner of A
 *         aHeight specifies the vertical dimension of A
 *         aWidth specifies the horizontal dimension of A
 *         bSWx and bSWy specify the SW (lower, left) corner of B
 *         bHeight specifies the vertical dimension of B
 *         bWidth specifies the horizontal dimension of B
 *       
 *   Returns:
 *         true if A and B share at least one point; false otherwise
 */
bool Overlap(int32_t aSWx, int32_t aSWy, uint32_t aHeight, uint32_t aWidth,
             int32_t bSWx, int32_t bSWy, uint32_t bHeight, uint32_t bWidth) {

   bool overlapDetected = false;

   // make sure the SW corner of rectangle B is smaller than the NE corner 
   // of rectangle A and make sure the NE corner of  rectangle B is greater 
   // than the SW corner of rectangle A
   if ( (bSWx <= (aSWx + aWidth) && bSWy <= (aSWy + aHeight)) && 
        ((bSWx + bWidth >= aSWx) && (bSWy + bHeight) >= aSWy) )
   {
      overlapDetected = true;
   }
   return overlapDetected;
}
