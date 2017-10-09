

int pin = 2;
int state = 1;
long debounceTime = 100;
long lastDebounce = 0;




void humanButtonPress()
{
  int current = digitalRead(pin);

  if(millis()>=lastDebounce+debounceTime)
  {
    Serial.println("Interrupt");
    lastDebounce = millis();
  }
}




void setup()
{
  pinMode(pin, INPUT_PULLUP);
  Serial.begin(9600);
  attachInterrupt(digitalPinToInterrupt(pin), humanButtonPress, FALLING);
  
}

void loop()
{
  
}




