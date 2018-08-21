import java.util.Random;

//  CARD. A playing card. It's immutable.

final class Card
{

//  RANK NAME. Printable names of card ranks. We don't use index 0.

    private static final String[] rankName =
            {
                    "",        //   0
                    "ace",     //   1
                    "two",     //   2
                    "three",   //   3
                    "four",    //   4
                    "five",    //   5
                    "six",     //   6
                    "seven",   //   7
                    "eight",   //   8
                    "nine",    //   9
                    "ten",     //  10
                    "jack",    //  11
                    "queen",   //  12
                    "king"     //  13
            };

    private int rank;  //  Card rank, between 1 and 13.

//  CARD. Constructor. Make a new CARD with a given RANK.

    public Card(int rank)
    {
        if (1 <= rank && rank <= 13)
        {
            this.rank = rank;
        }
        else
        {
            throw new IllegalArgumentException("Illegal rank.");
        }
    }

//  GET RANK. Return the RANK of this CARD.

    public int getRank()
    {
        return rank;
    }

//  TO STRING. Return a STRING that describes this CARD, for printing only.

    public String toString()
    {
        return rankName[rank];
    }
}

public class Deck {

    Card[] deck = new Card[52];
    private int top = 0;
    private Random random = new Random();

    public Deck (){
        for(int i = 0; i < 52; i++){
            deck[i] = new Card((i % 13) + 1);
        }
    }

    public Card deal(){
        top++;
        return deck[top-1];
    }

    public void shuffle(){
        if(top == 52){
            throw new IllegalStateException("Empty Deck");
        }
        for(int i = 52; i > 0; i--){
            int randomInt = random.nextInt(i);
            Card temp = deck[i-1];
            deck[i-1] = deck[randomInt];
            deck[randomInt] = temp;
        }
        return;
    }
}

public class Pile {

    private class Layer{

        private Card card;
        private Layer next;

        public Layer(Card card, Layer next){
            this.card = card;
            this.next = next;
        }
    }

    private Layer head;

    public Pile(){
        head = new Layer(null, null);
    }

    public void add(Card card){
        head.next = new Layer(card, head.next);
        return;
    }

    public Card turn(){
        if(isEmpty()){
            throw new IllegalStateException("No cards in pile");
        }
        Card returnCard = head.next.card;
        head.next = head.next.next;
        return returnCard;
    }
    public boolean isEmpty(){
        return head.next == null;
    }
}

public class Tableau {

    Pile[] tab;
    Deck deck;

    public Tableau(){
        tab = new Pile[13];
        for(int i = 0; i < 13; i++){
            tab[i] = new Pile();
        }
        deck = new Deck();
        deck.shuffle();
        for(int i = 0; i < 13; i++){
            for (int j = 0; j < 4; j++){
                tab[i].add(deck.deal());
            }
        }
    }

    private boolean hasWon(){
        for(int i = 0; i < 13; i++){
            if(!tab[i].isEmpty()){
                return false;
            }
        }
        return true;
    }

    public void play(){
        int p = 0;
        while(true){
            if(tab[p].isEmpty()){
                if(hasWon()){
                    System.out.println("You won!");
                    return;
                } else {
                    System.out.println("Pile " + (p+1) + " is empty. You lost!");
                    return;
                }
            }
            Card turnedCard = tab[p].turn();
            System.out.printf("Got %s from pile %d\n", turnedCard.toString(),p+1);
            p = turnedCard.getRank()-1;

        }
    }
}

public class Perditio {
    public static void main(String[] args) {

        for(int i = 0; i < 10; i++){
            new Tableau().play();
        }

    }
}
