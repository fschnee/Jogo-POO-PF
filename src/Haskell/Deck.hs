module Deck where

data Value = Ace | Two | Three | Four | Five | Six | Seven | Eigth | Nine | Ten | Jack | Queen | King
  deriving (Eq, Enum, Ord)
instance Show Value where
  show Ace   = "A"
  show Two   = "2"
  show Three = "3"
  show Four  = "4"
  show Five  = "5"
  show Six   = "6"
  show Seven = "7"
  show Eigth = "8"
  show Nine  = "9"
  show Ten   = "10"
  show Jack  = "J"
  show Queen = "Q"
  show King  = "K"
data Suit = Clubs | Diamonds | Hearts | Spades
  deriving (Eq, Enum)
instance Show Suit where
  show Clubs    = "♣"
  show Diamonds = "♦"
  show Hearts   = "♥"
  show Spades   = "♠"
data CardState = Hidden | Shown
  deriving (Eq, Show)

data Card = FrenchCard Value Suit CardState
instance Show Card where
  show (FrenchCard v s cs)
    | cs == Shown = show v ++ show s
    | otherwise   = "Hidden card"
instance Eq Card where
  (==) (FrenchCard v1 s1 _) (FrenchCard v2 s2 _)
    | v1 == v2 && s1 == s2 = True
    | otherwise            = False

type Deck = [Card]
type Hand = [Card]

isHidden :: Card -> Bool
isHidden (FrenchCard _ _ cs) = cs == Hidden

isShown :: Card -> Bool
isShown (FrenchCard _ _ cs) = cs == Shown

unhideCard :: Card -> Card
unhideCard (FrenchCard v s _) = FrenchCard v s Shown

hideCard :: Card -> Card
hideCard (FrenchCard v s _) = FrenchCard v s Hidden

makeDeck :: CardState -> Deck
makeDeck cs = [(FrenchCard v s cs) | v <- [Ace .. King], s <- [Clubs .. Spades]]

makeDecks :: Int -> CardState -> Deck
makeDecks 0 _  = []
makeDecks x cs = makeDecks (x - 1) cs ++ makeDeck cs

-- shuffle cardList -> swapsLeft -> swapIndexes (ja devem vir limitados tamanho da cardList) -> shuffledDeck
shuffle :: [Card] -> Int -> [Int] -> [Card]
shuffle [] _ _  = []
shuffle xs 0 _  = xs
shuffle xs _ [] = xs
shuffle (c:x) n (k:w) = shuffle ((fst popped):(snd popped)) (n - 1) w

  where
    popped = popindex (c:x) [] k

    -- Não precisa se preocupar em retornar um Maybe porque shuffle certifica
    -- que vão ser passados apenas vetores com elementos para a função
    -- TODO: otimizar, pois o operador (++) parece ser pesado
    popindex :: Eq t => [t] -> [t] -> Int -> (t, [t])
    popindex (c:x) seen 0 = (c, seen ++ x)
    popindex (c:x) seen num
      | x == []   = (c, seen)
      | otherwise = popindex x (seen ++ [c]) (abs num - 1)
