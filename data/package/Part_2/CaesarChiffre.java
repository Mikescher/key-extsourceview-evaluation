class CaesarChiffre {

    char[] valuesInput;
    char[] valuesOutput;

    /*@ normal_behaviour
      @
      @ requires offset >= 0;
      @ requires offset < 26;
      @
      @ requires valuesInput.length >= 2;
      @ requires valuesOutput        != valuesInput;
      @ requires valuesOutput.length == valuesInput.length;
      @
      @
      @ ensures valuesOutput.length == valuesInput.length;
      @
      @ // ( ensures modulo-shifted by $offset )
      @ ensures (\forall int i; 0 <= i && i < valuesOutput.length; ( valuesInput[i] + offset <= 'Z') ==> ( valuesOutput[i] == (valuesInput[i] + offset      ) ) );
      @ ensures (\forall int i; 0 <= i && i < valuesOutput.length; ( valuesInput[i] + offset >  'Z') ==> ( valuesOutput[i] == (valuesInput[i] + offset - 26 ) ) );
      @
      @ ensures (\forall int i; 0 <= i && i < valuesOutput.length; 'A' <= valuesOutput[i] && valuesOutput[i] <= 'Z');
      @
      @ ensures \result == valuesInput.length;
      @
      @ assignable valuesInput[*], valuesOutput[*];
      @*/
    int calcChiffre(int offset) {  

        int loopidx = 0;

        convertToUpper();

        /*@
          @ loop_invariant 0 <= loopidx;
          @ loop_invariant loopidx <= valuesInput.length;
          @
          @ loop_invariant ( \forall int i; 0 <= i && i < loopidx; ( valuesInput[i] + offset <= 'Z') ==> ( valuesOutput[i] == (valuesInput[i] + offset      ) ) );
          @ loop_invariant ( \forall int i; 0 <= i && i < loopidx; ( valuesInput[i] + offset >  'Z') ==> ( valuesOutput[i] == (valuesInput[i] + offset - 26 ) ) );
          @
          @ loop_invariant ( \forall int i; 0 <= i && i < loopidx; 'A' <= valuesOutput[i] && valuesOutput[i] <= 'Z' );
          @
          @ decreases valuesInput.length - loopidx;
          @
          @ assignable valuesOutput[*];
          @*/
        while (loopidx < valuesInput.length){  
            
            if (valuesInput[loopidx] <= 'Z' - offset) {
                int tmp1 = valuesInput[loopidx] + offset;
                valuesOutput[loopidx] = (char)tmp1;

            } else {

                int tmp2 = valuesOutput[loopidx] + offset - 26;
                valuesOutput[loopidx] = (char)tmp2;

            }


            loopidx++;
        }

        return valuesOutput.length;
    }

    /*@ normal_behaviour
      @
      @ requires valuesInput.length > 0;
      @
      @ ensures (\forall int i; 0 <= i && i < valuesInput.length; 'A' <= valuesInput[i] && valuesInput[i] <= 'Z');
      @
      @ assignable valuesInput[*];
      @*/
    void convertToUpper() {

      int idx = 0;

        /*@
          @ loop_invariant 0 <= idx;
          @ loop_invariant idx <= valuesInput.length;
          @
          @ loop_invariant ( \forall int i; 0 <= i && i < idx; 'A' <= valuesInput[i] && valuesInput[i] <= 'Z' );
          @
          @ decreases valuesInput.length - idx;
          @
          @ assignable valuesInput[*];
          @*/
        while (idx < valuesInput.length) {

          if ('A' <= valuesInput[idx] && valuesInput[idx] <= 'Z' ) {
            // okay
          } else if ('a' <= valuesInput[idx] && valuesInput[idx] <= 'z' )  {
            int tmp3 = valuesInput[idx] - 32;
            valuesInput[idx] = (char)tmp3;
          } else {
            valuesInput[idx] = 'A';
          }

          idx++;

        }

    } 

    public static void main(String [] args) {
        CaesarChiffre ex = new CaesarChiffre();

        ex.valuesOutput = "HelloWorld".toCharArray();
        System.out.println( new String(ex.valuesOutput) );

        for(int i=0; i < 4; i++) {
          ex.valuesInput = ex.valuesOutput;
          ex.valuesOutput = new char[ex.valuesInput.length];
          ex.calcChiffre(13);

          System.out.println( new String(ex.valuesOutput)  );
        }
    }
}
