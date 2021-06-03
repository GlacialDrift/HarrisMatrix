# HarrisMatrix
Interfaces and Classes for classic matrix multiplication as well as graphics matrix manipulation

Classic matrix multiplication is handled by the HMatrix interface, which contains NMatrix (for nxm matrices), and SMatrix (for mxm matrices). The two classes interact for mathematical calculations, often creating new matrices of either type as necessary. These classes are of my personal implementation based on my own knowledge of linear algebra.



The PMatrix group is used for graphics rendering and is based on the ["Processing"](https://processing.org/) framework for java. PMatrix2D is actually a 3x3 matrix for rotations and multiplications. PMatrix3D is actually a 4x4 matrix for 3D graphics manipulation (I believe on the basis of quaternions). 

The PVector class can be used either as a true vector class (with support for 2D and 3D implementations), or as a position-storing class that allows for direct support of distance calculations between "points" specified by the vector location.
