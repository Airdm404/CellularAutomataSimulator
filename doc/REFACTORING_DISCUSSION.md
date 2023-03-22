## Lab Discussion
### simulation 11 
### Edem Ahorlu eka13
### Yixuan Li yl600
### Christian Welch cw356


### Issues in Current Code

#### Method or Class
 * Design issues
 
 Some of the method is still passing specific data structure like 2D Arraylist

 * Design issue

 Reflection is used in model is not throwing the exception properly, it simply calls printStackTrace().

### Refactoring Plan

 * What are the code's biggest issues?
 
    The code is not handling encapsulation very well, some of the implementation details (specific data structure) are exposed to high-level code. The `AllCells` class is not implementing an immutable interface.

 * Which issues are easy to fix and which are hard?
 
    Encapsulation problems might be harder to fix, since it requires creating new class for specific data structure and create an interface for `AllCells` class. 
    
    Making code properly throwing exceptions should be easy to implement. 

 * What are good ways to implement the changes "in place"?
 
    Using IntelliJ's refactoring feature to refactore the code. 


### Refactoring Work

 * Issue chosen: Fix and Alternatives
 Created a CellStateStructure class to hide the data structures used.

    

 * Issue chosen: Fix and Alternatives
 Created a custom exception class for all reflections made in the model package.
