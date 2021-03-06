PGDMP                     	    x            Try    9.5.23    9.5.23 >    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            ?           1262    17398    Try    DATABASE     ?   CREATE DATABASE "Try" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Chinese (Simplified)_China.936' LC_CTYPE = 'Chinese (Simplified)_China.936';
    DROP DATABASE "Try";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            ?           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6            ?           0    0    SCHEMA public    ACL     ?   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6                        3079    12355    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            ?           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            ?            1259    17404    author    TABLE     D   CREATE TABLE public.author (
    personid character(10) NOT NULL
);
    DROP TABLE public.author;
       public         postgres    false    6            ?            1259    17446    book    TABLE     ?   CREATE TABLE public.book (
    bookid character(10) NOT NULL,
    title character varying(30),
    description character varying(100),
    personid character(10)
);
    DROP TABLE public.book;
       public         postgres    false    6            ?            1259    17424    customer    TABLE     ?   CREATE TABLE public.customer (
    personid character(10) NOT NULL,
    email character(30) NOT NULL,
    address character(50)
);
    DROP TABLE public.customer;
       public         postgres    false    6            ?            1259    17466    edition    TABLE     ?   CREATE TABLE public.edition (
    isbn character(20) NOT NULL,
    typle character varying(10),
    price integer NOT NULL,
    publisher character varying(10) NOT NULL,
    bookid character(10) NOT NULL
);
    DROP TABLE public.edition;
       public         postgres    false    6            ?            1259    17456    genre    TABLE     ?   CREATE TABLE public.genre (
    genreid character(10) NOT NULL,
    genrename character varying(20) NOT NULL,
    bookid character(10)
);
    DROP TABLE public.genre;
       public         postgres    false    6            ?            1259    17524 	   orderitem    TABLE     ?   CREATE TABLE public.orderitem (
    orderitemid character(10) NOT NULL,
    orderitemname character varying(20) NOT NULL,
    orderid character(10) NOT NULL,
    isbn character(20) NOT NULL,
    quantity integer NOT NULL,
    charge integer NOT NULL
);
    DROP TABLE public.orderitem;
       public         postgres    false    6            ?            1259    17434    orders    TABLE     ?   CREATE TABLE public.orders (
    orderid character(10) NOT NULL,
    personid character(10) NOT NULL,
    datetime date NOT NULL,
    totalcharge integer NOT NULL
);
    DROP TABLE public.orders;
       public         postgres    false    6            ?            1259    17399    person    TABLE     s   CREATE TABLE public.person (
    personid character(10) NOT NULL,
    personname character varying(10) NOT NULL
);
    DROP TABLE public.person;
       public         postgres    false    6            ?            1259    17509    recommendation    TABLE     ?   CREATE TABLE public.recommendation (
    recid character(10) NOT NULL,
    rdate date NOT NULL,
    personid character(10),
    bookid character(10)
);
 "   DROP TABLE public.recommendation;
       public         postgres    false    6            ?            1259    17488    review    TABLE     Q  CREATE TABLE public.review (
    reviewid character(10) NOT NULL,
    rating numeric(10,1) NOT NULL,
    review character varying(50),
    personid character(10) NOT NULL,
    listid character(10) NOT NULL,
    bookid character(10) NOT NULL,
    CONSTRAINT check_rating CHECK (((rating >= (0)::numeric) AND (rating <= (5)::numeric)))
);
    DROP TABLE public.review;
       public         postgres    false    6            ?            1259    17478 
   reviewlist    TABLE     ?   CREATE TABLE public.reviewlist (
    listid character(10) NOT NULL,
    reviewname character varying(20) NOT NULL,
    description character varying(50),
    personid character(10)
);
    DROP TABLE public.reviewlist;
       public         postgres    false    6            ?            1259    17414    staff    TABLE     C   CREATE TABLE public.staff (
    personid character(10) NOT NULL
);
    DROP TABLE public.staff;
       public         postgres    false    6            ?          0    17404    author 
   TABLE DATA               *   COPY public.author (personid) FROM stdin;
    public       postgres    false    182   ?E       ?          0    17446    book 
   TABLE DATA               D   COPY public.book (bookid, title, description, personid) FROM stdin;
    public       postgres    false    186   ?E       ?          0    17424    customer 
   TABLE DATA               <   COPY public.customer (personid, email, address) FROM stdin;
    public       postgres    false    184   [F       ?          0    17466    edition 
   TABLE DATA               H   COPY public.edition (isbn, typle, price, publisher, bookid) FROM stdin;
    public       postgres    false    188   G       ?          0    17456    genre 
   TABLE DATA               ;   COPY public.genre (genreid, genrename, bookid) FROM stdin;
    public       postgres    false    187   ?G       ?          0    17524 	   orderitem 
   TABLE DATA               `   COPY public.orderitem (orderitemid, orderitemname, orderid, isbn, quantity, charge) FROM stdin;
    public       postgres    false    192    H       ?          0    17434    orders 
   TABLE DATA               J   COPY public.orders (orderid, personid, datetime, totalcharge) FROM stdin;
    public       postgres    false    185   ?H       ?          0    17399    person 
   TABLE DATA               6   COPY public.person (personid, personname) FROM stdin;
    public       postgres    false    181   I       ?          0    17509    recommendation 
   TABLE DATA               H   COPY public.recommendation (recid, rdate, personid, bookid) FROM stdin;
    public       postgres    false    191   ?I       ?          0    17488    review 
   TABLE DATA               T   COPY public.review (reviewid, rating, review, personid, listid, bookid) FROM stdin;
    public       postgres    false    190   J       ?          0    17478 
   reviewlist 
   TABLE DATA               O   COPY public.reviewlist (listid, reviewname, description, personid) FROM stdin;
    public       postgres    false    189   ?J       ?          0    17414    staff 
   TABLE DATA               )   COPY public.staff (personid) FROM stdin;
    public       postgres    false    183   @K       ?           2606    17472    edition_isbn_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.edition
    ADD CONSTRAINT edition_isbn_key UNIQUE (isbn);
 B   ALTER TABLE ONLY public.edition DROP CONSTRAINT edition_isbn_key;
       public         postgres    false    188    188            ?           2606    17440    orders_orderid_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_orderid_key UNIQUE (orderid);
 C   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_orderid_key;
       public         postgres    false    185    185            ?           2606    17408 	   pk_author 
   CONSTRAINT     T   ALTER TABLE ONLY public.author
    ADD CONSTRAINT pk_author PRIMARY KEY (personid);
 :   ALTER TABLE ONLY public.author DROP CONSTRAINT pk_author;
       public         postgres    false    182    182            ?           2606    17450    pk_book 
   CONSTRAINT     N   ALTER TABLE ONLY public.book
    ADD CONSTRAINT pk_book PRIMARY KEY (bookid);
 6   ALTER TABLE ONLY public.book DROP CONSTRAINT pk_book;
       public         postgres    false    186    186            ?           2606    17428    pk_customer 
   CONSTRAINT     X   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT pk_customer PRIMARY KEY (personid);
 >   ALTER TABLE ONLY public.customer DROP CONSTRAINT pk_customer;
       public         postgres    false    184    184            ?           2606    17470 
   pk_edition 
   CONSTRAINT     Z   ALTER TABLE ONLY public.edition
    ADD CONSTRAINT pk_edition PRIMARY KEY (isbn, bookid);
 <   ALTER TABLE ONLY public.edition DROP CONSTRAINT pk_edition;
       public         postgres    false    188    188    188            ?           2606    17460    pk_genre 
   CONSTRAINT     Q   ALTER TABLE ONLY public.genre
    ADD CONSTRAINT pk_genre PRIMARY KEY (genreid);
 8   ALTER TABLE ONLY public.genre DROP CONSTRAINT pk_genre;
       public         postgres    false    187    187                       2606    17528    pk_orderitem 
   CONSTRAINT     l   ALTER TABLE ONLY public.orderitem
    ADD CONSTRAINT pk_orderitem PRIMARY KEY (orderitemid, orderid, isbn);
 @   ALTER TABLE ONLY public.orderitem DROP CONSTRAINT pk_orderitem;
       public         postgres    false    192    192    192    192            ?           2606    17438 	   pk_orders 
   CONSTRAINT     ]   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT pk_orders PRIMARY KEY (orderid, personid);
 :   ALTER TABLE ONLY public.orders DROP CONSTRAINT pk_orders;
       public         postgres    false    185    185    185            ?           2606    17403 	   pk_person 
   CONSTRAINT     T   ALTER TABLE ONLY public.person
    ADD CONSTRAINT pk_person PRIMARY KEY (personid);
 :   ALTER TABLE ONLY public.person DROP CONSTRAINT pk_person;
       public         postgres    false    181    181                       2606    17513    pk_recommendation 
   CONSTRAINT     a   ALTER TABLE ONLY public.recommendation
    ADD CONSTRAINT pk_recommendation PRIMARY KEY (recid);
 J   ALTER TABLE ONLY public.recommendation DROP CONSTRAINT pk_recommendation;
       public         postgres    false    191    191            ?           2606    17493 	   pk_review 
   CONSTRAINT     n   ALTER TABLE ONLY public.review
    ADD CONSTRAINT pk_review PRIMARY KEY (reviewid, personid, listid, bookid);
 :   ALTER TABLE ONLY public.review DROP CONSTRAINT pk_review;
       public         postgres    false    190    190    190    190    190            ?           2606    17482    pk_reviewlist 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reviewlist
    ADD CONSTRAINT pk_reviewlist PRIMARY KEY (listid);
 B   ALTER TABLE ONLY public.reviewlist DROP CONSTRAINT pk_reviewlist;
       public         postgres    false    189    189            ?           2606    17418    pk_staff 
   CONSTRAINT     R   ALTER TABLE ONLY public.staff
    ADD CONSTRAINT pk_staff PRIMARY KEY (personid);
 8   ALTER TABLE ONLY public.staff DROP CONSTRAINT pk_staff;
       public         postgres    false    183    183                       2606    17529    fk1_orderitem    FK CONSTRAINT     ?   ALTER TABLE ONLY public.orderitem
    ADD CONSTRAINT fk1_orderitem FOREIGN KEY (orderid) REFERENCES public.orders(orderid) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.orderitem DROP CONSTRAINT fk1_orderitem;
       public       postgres    false    192    2033    185                       2606    17514    fk1_recommendation    FK CONSTRAINT     ?   ALTER TABLE ONLY public.recommendation
    ADD CONSTRAINT fk1_recommendation FOREIGN KEY (personid) REFERENCES public.customer(personid) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.recommendation DROP CONSTRAINT fk1_recommendation;
       public       postgres    false    191    184    2031                       2606    17494 
   fk1_review    FK CONSTRAINT     ?   ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk1_review FOREIGN KEY (personid) REFERENCES public.person(personid) ON DELETE CASCADE;
 ;   ALTER TABLE ONLY public.review DROP CONSTRAINT fk1_review;
       public       postgres    false    181    190    2025                       2606    17534    fk2_orderitem    FK CONSTRAINT     ?   ALTER TABLE ONLY public.orderitem
    ADD CONSTRAINT fk2_orderitem FOREIGN KEY (isbn) REFERENCES public.edition(isbn) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.orderitem DROP CONSTRAINT fk2_orderitem;
       public       postgres    false    2041    192    188                       2606    17519    fk2_recommendation    FK CONSTRAINT     ?   ALTER TABLE ONLY public.recommendation
    ADD CONSTRAINT fk2_recommendation FOREIGN KEY (bookid) REFERENCES public.book(bookid) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.recommendation DROP CONSTRAINT fk2_recommendation;
       public       postgres    false    186    191    2037                       2606    17499 
   fk2_review    FK CONSTRAINT     ?   ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk2_review FOREIGN KEY (listid) REFERENCES public.reviewlist(listid) ON DELETE CASCADE;
 ;   ALTER TABLE ONLY public.review DROP CONSTRAINT fk2_review;
       public       postgres    false    189    2045    190                       2606    17504 
   fk3_review    FK CONSTRAINT     ?   ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk3_review FOREIGN KEY (bookid) REFERENCES public.book(bookid) ON DELETE CASCADE;
 ;   ALTER TABLE ONLY public.review DROP CONSTRAINT fk3_review;
       public       postgres    false    190    186    2037                       2606    17409 	   fk_author    FK CONSTRAINT     ?   ALTER TABLE ONLY public.author
    ADD CONSTRAINT fk_author FOREIGN KEY (personid) REFERENCES public.person(personid) ON DELETE CASCADE;
 :   ALTER TABLE ONLY public.author DROP CONSTRAINT fk_author;
       public       postgres    false    182    181    2025                       2606    17451    fk_book    FK CONSTRAINT     ?   ALTER TABLE ONLY public.book
    ADD CONSTRAINT fk_book FOREIGN KEY (personid) REFERENCES public.author(personid) ON DELETE CASCADE;
 6   ALTER TABLE ONLY public.book DROP CONSTRAINT fk_book;
       public       postgres    false    186    2027    182                       2606    17429    fk_customer    FK CONSTRAINT     ?   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_customer FOREIGN KEY (personid) REFERENCES public.person(personid) ON DELETE CASCADE;
 >   ALTER TABLE ONLY public.customer DROP CONSTRAINT fk_customer;
       public       postgres    false    184    2025    181            
           2606    17473 
   fk_edition    FK CONSTRAINT     ?   ALTER TABLE ONLY public.edition
    ADD CONSTRAINT fk_edition FOREIGN KEY (bookid) REFERENCES public.book(bookid) ON DELETE CASCADE;
 <   ALTER TABLE ONLY public.edition DROP CONSTRAINT fk_edition;
       public       postgres    false    2037    186    188            	           2606    17461    fk_genre    FK CONSTRAINT     ?   ALTER TABLE ONLY public.genre
    ADD CONSTRAINT fk_genre FOREIGN KEY (bookid) REFERENCES public.book(bookid) ON DELETE CASCADE;
 8   ALTER TABLE ONLY public.genre DROP CONSTRAINT fk_genre;
       public       postgres    false    187    186    2037                       2606    17441 	   fk_orders    FK CONSTRAINT     ?   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_orders FOREIGN KEY (personid) REFERENCES public.customer(personid) ON DELETE CASCADE;
 :   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk_orders;
       public       postgres    false    184    2031    185                       2606    17483    fk_reviewlist    FK CONSTRAINT     ?   ALTER TABLE ONLY public.reviewlist
    ADD CONSTRAINT fk_reviewlist FOREIGN KEY (personid) REFERENCES public.staff(personid) ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.reviewlist DROP CONSTRAINT fk_reviewlist;
       public       postgres    false    183    2029    189                       2606    17419    fk_staff    FK CONSTRAINT     ?   ALTER TABLE ONLY public.staff
    ADD CONSTRAINT fk_staff FOREIGN KEY (personid) REFERENCES public.person(personid) ON DELETE CASCADE;
 8   ALTER TABLE ONLY public.staff DROP CONSTRAINT fk_staff;
       public       postgres    false    183    181    2025            ?       x?30 C ???Lc?̌???? ?Q[      ?   ?   x??̱? ?????MUum7u????"?5`
???&N??]??W'!v????]???f̀?f??X5R??I????Ԏ?xeT?
?}?LZyR???$ݻ^????+1?ֿ?!??b??nf[???C?'Io???8?_?FJ?      ?   ?   x??н?0??<?} B??q]Htq?ҐƖJSb|{1?????.???!??8??/?"+?>9;A???6F????
q9??sM?U??ߩx?YM*y?|l??QN1??/?????S"?J?'??Tɻ??2^??0?3@??ʊ?Jm?????S\ 2???Ō~?Q??,?$???tcd      ?   o   x?e˭? @?|y
_ wAn??O2X,?9v͠O????Գ?????I??_0?|?? ?5L??#C7 ???P?!?І?%g4.765zޮ?X??#????U:e
???B?0?      ?   S   x?s?4562U N?????????<N'os???;H?X?kJir"\?I??3$59#/?'????I????=... ??      ?   ?   x?eͽ?0??y??U?????eCl?,QUTQ????ʍ>}??t?????.???2h?p?RJ?GChX-???}?Ӹ?q??Z/?A??:?]??m??? ?O??9rU?UQ???n?J?/??.h      ?   e   x?u???0?7L?R?	??t?9J?'???8?*?r?! ?a?Y?T?H??"?????l???????r	?|??.??j?d?b\??9?9?'3?21?      ?      x?=?1?0???>?O????	$X"?*K?L??qSdo??<3s?ȁ?,:U9	??1?V?V??Hp?T?-???'?~?s=S?{???<G?C?	?????,???h?ͱy??U?зp)j+D??+J      ?   _   x?U̱? Dј??p?I?̌????Q?l???????z????Na?:?g?f=???VJ??:????.???.?n?66<?pК~m????s?Vg=      ?   ?   x?}?=?0?9??B???:?tr??ڒ@???m?$??M???]K%	"??N!???????e?b??Zq& ??????Q?_+nW??JB??%????W???&뻛?\FeD?05U)??Ѫʚ???Mњ??k?z?W?ڻ͜???*s5Mܑ`???VK?      ?   w   x?5?0U N???????????b???????????"?dqIiJj^	??????????WX??????1t?t[?[?;8???<?(?LL? f????1? ?c?=... ??M?      ?       x?322204362T?2?2?Lc?=... ?Js     