long i_time = 0; // if time
long e_time = 0; // else time

unsigned long c_time; //variable per millis
const int digitalPin = 3; // pin A3
const int pBuzzer = 4;

int max_time = 5000; // temps maxim de buzzer
int reading = 0;
unsigned int freq_0 = 330;
unsigned int freq_1 = 100;
int duration = 5000;

void setup() {
  Serial.begin(9600);
  pinMode(A0, INPUT);
  pinMode(pBuzzer, OUTPUT);

}

void loop() {
  int c_time = millis();
  reading = analogRead(A0);
  // Serial.println(reading);
  //300 = llindar al atzar
  
  if ( reading > 300) {
    if (c_time - i_time < max_time) {
      
      i_time = c_time - i_time; // 0 = 0-0 ; 0 = 100-0  ; x = 200-100
      //Serial.println(i_time);
      e_time = 0;
      duration = duration + 100;
      tone(pBuzzer, freq_0, duration);
    }

  } else {
    e_time = c_time - e_time; 
    i_time = 0; // if time = 0
    
    tone(pBuzzer, freq_1, 100);
    Serial.print(freq_1);
    Serial.print(", "); 
    Serial.println();
  }



  delay(100);
}
