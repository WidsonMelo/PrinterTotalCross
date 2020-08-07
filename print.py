import RPi.GPIO as GPIO         
import time                     

GPIO.setmode(GPIO.BOARD)   
GPIO.setwarnings(False)    
GPIO.setup(18, GPIO.OUT)   

GPIO.output(18, 1)         
time.sleep(1)               
GPIO.output(18, 0)               
time.sleep(1)                        