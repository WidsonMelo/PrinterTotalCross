import RPi.GPIO as GPIO         
import time                     

GPIO.setmode(GPIO.BOARD)   
GPIO.setwarnings(False)    
GPIO.setup(20, GPIO.OUT)   

GPIO.output(20, 1)         
time.sleep(1)               
GPIO.output(20, 0)               
time.sleep(1)