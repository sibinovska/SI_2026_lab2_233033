# SI_2026_lab2_233033

Име и презиме: Ангела Сибиновска  
Број на индекс: 233033  

## 2. Control Flow Graph

### Control Flow Graph за `searchBookByTitle`

![CFG за searchBookByTitle](images/searchBookByTitle_CFG.png)

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
