# User story: Sort Results

[◄ back to index](../index.md)

## User story description:
The future student wants the results  to be sorted in order of which job/it sector suits him.

## Used modules
- Java AWT
    - Graphics2D
- Comparator
- 
## PlantUML:
<img src="../../assets/plantumlsort.png" width="750">


## Class diagram:
<img src="../../assets/classSort.png" width="500">

## The Java Class:
Het gebruik van Integer[] betekent dat het een array is van Integer objecten, niet van de primitieve int waarden.

sortedIndexes[i] = i;: Deze regel kent de waarde van i toe aan het i-de element van de sortedIndexes array. <br>
Aangezien i de indexwaarde is, stelt dit effectief elk element van sortedIndexes in op zijn overeenkomstige index in de score array. \
<img src="../../assets/sortArrayForLoop.png" width="750" >


Dit gedeelte definieert de sorteerlogica. Het maakt gebruik van de comparingLong methode van de Comparator klasse om een comparator
te maken op basis van de lange waarden die uit de score array worden gehaald. <br>

Het lambda-expressiegedeelte i -> -score[i] geeft aan hoe de waarden moeten worden vergeleken. 
Door het negatieve teken (-) voor score[i] te plaatsen, worden de waarden in omgekeerde volgorde gesorteerd, van hoog naar laag.\
<img src="../../assets/SortWithLambda.png" width="750" >
