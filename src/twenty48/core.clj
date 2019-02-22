(ns twenty48.core
  (:gen-class))

(def remove-zero (partial remove zero?))

(def pair-of-two (partial partition-all 2))

(def pair-consecutive-identical-elements (partial partition-by identity))

(def add-elements-of-inner-array (partial map (partial apply +)))

(def fill-zero-in-list (comp (partial take 4) (partial flatten) (partial conj (repeat 4 0))))

(def transpose-grid (partial apply map vector))

(transpose-grid `((2 3)(4 5)))

(def move-left (comp (partial fill-zero-in-list)
                     (partial add-elements-of-inner-array)
                     (partial mapcat pair-of-two)
                     (partial pair-consecutive-identical-elements)
                     (partial remove-zero)))

(def move-right (comp (partial reverse)
                      (partial move-left)
                      (partial reverse)))

(defn move-grid-right
  "Moves an entire grid to the right"
  [grid]
  (map move-right grid))

(defn move-grid-left
  "Moves an entire grid to the left"
  [grid]
  (map move-left grid))

(defn move-grid-down
  "Moves an entire grid down"
  [grid]
  ((comp (partial transpose-grid)
         (partial move-grid-right)
         (partial transpose-grid)) grid ))

(defn move-grid-up
  "Moves an entire grid up"
  [grid]
  ((comp (partial transpose-grid)
         (partial move-grid-left)
         (partial transpose-grid)) grid))
