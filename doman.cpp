#include <cv.h>
#include <highgui.h>
#include <iostream>
#include <algorithm>
#include <vector>
#include <ctime>
#include <cstdlib>
#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <dirent.h>

using namespace std;
using namespace cv;


//FUNCIONES

void generateSession(vector<int> &myvector, int MAX);
int listImages();
int myRandom(int i);
string itos(int num);

//MAIN

int main(){
  
  string dir = "./europa/";
  int numImagenes = listImages();
  
  Mat image;
  
  vector<int> orden;
  
  /*****************************************/
  
  if(numImagenes){
    cout << "Todo bien y correcto, num imagenes = " << numImagenes << endl;
    generateSession(orden, numImagenes);
    cout << "Tamano del vector = " << orden.size() << endl;
  }
  else{
    cout << "Error, no se ha encontrado el directorio o imagenes en el directorio" << endl;
  }
  
  for(int i = 0; i < numImagenes; i++){
    cout << "Mostrando la imagen " << orden.back() << endl;
    image = imread(dir+itos(orden.back())+".png", CV_LOAD_IMAGE_UNCHANGED);
    if(!image.data){
      cout << "Imagen " << orden.back() << ".jpg no encontrada" << endl;
    }
    else{
      //namedWindow("Imagen "+char(orden.back()+48), WINDOW_AUTOSIZE);
      namedWindow("Imagen "+char(orden.back()+48), WINDOW_NORMAL);
      cvSetWindowProperty("Imagen "+char(orden.back()+48), CV_WND_PROP_FULLSCREEN, CV_WINDOW_FULLSCREEN);
      imshow("Imagen "+char(orden.back()+48), image);
      waitKey(0);
      
    }
    orden.pop_back();
  }
  
  destroyAllWindows();
  return 0;
}

int listImages(){
  
  DIR *dp;
  struct dirent *ep;
  int cuenta = 0;
  
  dp = opendir("./europa/");
  
  if(dp != NULL){
    while(ep = readdir(dp)){
      if( ep->d_name[0] != '.') // excluding from linux current and back directory
	cuenta++;
    }
  }
  else{
    cuenta = -1;
  }
  
  return cuenta;
}

string itos(int num){
  string result;
  stringstream convert;
  
  convert << num;
  
  return convert.str();
}

void generateSession(vector<int> &myvector, int MAX) {
  srand(unsigned(time(0)));

  for (int i=0; i<MAX; ++i) myvector.push_back(i); // 0 1 2 3 4 5 6 7 8 9

  // using built-in random generator:
  random_shuffle ( myvector.begin(), myvector.end() );

  // using myRandom:
  random_shuffle ( myvector.begin(), myvector.end(), myRandom);
}

int myRandom (int i) { return rand()%i;}