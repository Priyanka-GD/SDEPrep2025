1. Functional Requirements

Prefix-Based Search: Uses a Trie data structure to efficiently find all words starting with a specific user-inputted prefix.

Dynamic Dictionary: Supports adding new words at runtime via the insertWord method; the system is not restricted to a static, pre-defined list.

Frequency Tracking: Automatically increments the "weight" of a word every time it is inserted, allowing the system to learn which words are more popular over time.

Configurable Ranking: Implements the Strategy Pattern, allowing the user to switch between:

Frequency-based ranking: Most used words appear first.

Alphabetical ranking: Words appear in dictionary order.

Configurable Limits: Allows the caller to specify exactly how many suggestions to return (e.g., Top 5 or Top 10), with a fallback to a default value.

2. Constraints & Business Logic

Case Insensitivity: All inputs (both during insertion and searching) are normalized to lowercase, ensuring that "Apple", "APPLE", and "apple" are treated as the same entity.

Character Support: Restricted to English characters for the current scope, simplifying the character mapping logic.

No Deletions: Per the interviewer’s instruction, the system focuses on growth and retrieval, skipping word removal or manual frequency updates.


3. Technical Design (LLD)

FeatureImplementation DetailSearch Complexity O(L) where L is the length of the prefix.

Ranking Complexity O(N log N) where N is the number of candidate words at a node (can be optimized with a Heap).

ExtensibilityAdding a new ranking logic (e.g., "Recently Used") only requires a new class implementing SuggestionStrategy.

Data StorageTrade-off made for speed: Nodes store a frequency map of all child words to avoid deep tree traversals during search.