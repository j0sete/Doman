#!/usr/bin/python

import numpy as np

import cv, cv2, os, random

def show(dir, order):
  for x in xrange(len(order)):
    img = cv2.imread(DIR+str(order[x])+'.png')
    cv2.namedWindow(DIR+str(order[x]), cv2.WND_PROP_FULLSCREEN)          
    cv2.setWindowProperty(DIR+str(order[x]), cv2.WND_PROP_FULLSCREEN, cv2.cv.CV_WINDOW_FULLSCREEN)
    cv2.imshow(DIR+str(order[x]),img)
    cv2.waitKey(0)
    cv2.destroyAllWindows()

def generateSesion(numFiles):
  order=[i for i in range(numFiles)]
  random.shuffle(order)
  return order

### MAIN

DIR = './europa/'
numFiles = len([name for name in os.listdir(DIR) if os.path.isfile(os.path.join(DIR, name))])

order = generateSesion(numFiles)
show(DIR, order)
  
cv2.destroyAllWindows()