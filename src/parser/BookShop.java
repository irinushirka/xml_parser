package parser;

import java.util.ArrayList;

public class BookShop{
    public static int books_amount = 0;
    private ArrayList<Book> books;
    BookShop(){
        books = new ArrayList<>();
    }
    BookShop(ArrayList<Book> books){
        this.books = books;
        books_amount = this.books.size();
    }
    public void addBook(Book book){
        books.add(book);
        books_amount++;
    }
    public void showBooksData(){
        for (int i = 0; i < books_amount; i++) {
            System.out.println("\t\t--> Book № " + (i + 1) + " <--");
            System.out.println("Book title: " + books.get(i).getTitle());
            System.out.println("Book author: " + books.get(i).getAuthor());
            System.out.println("Book price: " + books.get(i).getPrice());
            System.out.println("Pages anount: " + books.get(i).getPages());
        }
    }
    public int countTotalCost(){
        int total_cost = 0;
        for (Book b: books) {
            total_cost += b.getPrice();
        }
        return total_cost;
    }
}
