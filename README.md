# PAOproj
Starting project for Java language course

## Requirement 0
I use Maps, Sets and Lists like this: \

<ul>
<li>
  SETS - for loading into memory the Instances of the class, from the *.csv files\
  E.g.: lines 120, 152, 182 from the *Main.java* file
</li>
<li>
  MAPS - for getting the associative table, relations (representing instances of the PertainsTo Class)
  E.g.: lines 132, 133, 154 from the *Main.java* file
</li>
<li>
  LISTS - 16, 27 from the *Main.java* file and 40, 41 from the *GenericWriter.java* file
</li>
</ul>

## Requirement 1
The implementation of a generic read/write mechanism using Generic java Classes: \
This is treated in the files *GenericReader.java* and *GenericWriter.java*
! each of the previous files have a defined main() function that create .csv files.
  GenericWriter's main() creates instances and saves them, whereas GenericReader loads them from memory and saves them in other .csv files,
  so as to prove that they work correctly.


## Requirement 2
Create a logger to jot down the CRUD actions: \
  this is done with the Logger class (model.Logger),
    and the CRUD operations are implemented in the Main class (model.Main)
