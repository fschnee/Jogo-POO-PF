module Deck where

data Value = Ace | Two | Three | Four | Five | Six | Seven | Eigth | Nine | Ten | Jack | Queen | King
  deriving (Eq, Show, Enum, Ord)
data Suit = Clubs | Diamonds | Hearts | Spades
  deriving (Eq, Show, Enum)
data CardState = Hidden | Shown
  deriving (Eq, Show)

data Card = FrenchCard Value Suit CardState
instance Show Card where
  show (FrenchCard v s cs)
    | cs == Shown = show v ++ " of " ++ show s
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

showCard :: Card -> Card
showCard (FrenchCard v s _) = FrenchCard v s Shown

hideCard :: Card -> Card
hideCard (FrenchCard v s _) = FrenchCard v s Hidden

makeDeck :: CardState -> Deck
makeDeck cs = [(FrenchCard v s cs) | v <- [Ace .. King], s <- [Clubs .. Spades]]

makeDecks :: Int -> CardState -> Deck
makeDecks 0 _  = []
makeDecks x cs = makeDecks (x - 1) cs ++ makeDeck cs

-- shuffle :: [Card] -> [Card]
