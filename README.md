# SI_2026_lab2_233033

Име и презиме: Ангела Сибиновска  
Број на индекс: 233033  

## 2. Control Flow Graph

### Control Flow Graph за `searchBookByTitle`

```mermaid
flowchart TD
    A([Start]) --> B{title.isEmpty()}
    B -- true --> C[throw IllegalArgumentException]
    B -- false --> D[results = new ArrayList]
    D --> E{има следна книга во books?}
    E -- true --> F{book.getTitle().equalsIgnoreCase(title)}
    F -- true --> G{!book.isBorrowed()}
    G -- true --> H[results.add(book)]
    H --> E
    G -- false --> E
    F -- false --> E
    E -- false --> I{results.isEmpty()}
    I -- true --> J[return null]
    I -- false --> K[return results]
    C --> L([End])
    J --> L
    K --> L
```

### Control Flow Graph за `borrowBook`

```mermaid
flowchart TD
    A([Start]) --> B{title.isEmpty()}
    B -- true --> C[throw IllegalArgumentException]
    B -- false --> D{author.isEmpty()}
    D -- true --> C
    D -- false --> E{има следна книга во books?}
    E -- true --> F{book.getTitle().equalsIgnoreCase(title)}
    F -- true --> G{book.getAuthor().equalsIgnoreCase(author)}
    G -- true --> H{!book.isBorrowed()}
    H -- true --> I[book.setBorrowed(true)]
    I --> J[print Borrowed successfully]
    J --> K[return]
    H -- false --> L[throw RuntimeException: Book is already borrowed]
    G -- false --> E
    F -- false --> E
    E -- false --> M[throw RuntimeException: Book not found]
    C --> N([End])
    K --> N
    L --> N
    M --> N
```
