--
-- PostgreSQL database dump
--

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS '';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bill; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bill (
    id integer NOT NULL,
    order_id integer NOT NULL,
    client_name character varying(100) NOT NULL,
    product_name character varying(100) NOT NULL,
    quantity integer NOT NULL,
    price numeric NOT NULL,
    date timestamp without time zone NOT NULL
);


ALTER TABLE public.bill OWNER TO postgres;

--
-- Name: bill_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bill_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.bill_id_seq OWNER TO postgres;

--
-- Name: bill_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bill_id_seq OWNED BY public.bill.id;


--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    age integer NOT NULL
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    price numeric NOT NULL,
    stock integer NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: warehouse_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.warehouse_order (
    id integer NOT NULL,
    client_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    order_date timestamp without time zone NOT NULL
);


ALTER TABLE public.warehouse_order OWNER TO postgres;

--
-- Name: warehouse_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.warehouse_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.warehouse_order_id_seq OWNER TO postgres;

--
-- Name: warehouse_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.warehouse_order_id_seq OWNED BY public.warehouse_order.id;


--
-- Name: bill id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bill ALTER COLUMN id SET DEFAULT nextval('public.bill_id_seq'::regclass);


--
-- Name: client id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: warehouse_order id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouse_order ALTER COLUMN id SET DEFAULT nextval('public.warehouse_order_id_seq'::regclass);


--
-- Data for Name: bill; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bill VALUES (1, 1, 'Maria123', 'mere ', 13, 45.5, '2026-05-21 10:12:50.972');
INSERT INTO public.bill VALUES (2, 2, 'Marius', 'coca-cola doza', 17, 102, '2026-05-21 10:13:22.593');


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.client VALUES (2, 'Maria123', 'mariapop@yahoo.com', 27);
INSERT INTO public.client VALUES (3, 'Ionut6', 'ionut84@gmail.com', 19);
INSERT INTO public.client VALUES (1, 'Marius', 'marius123@gmail.com', 22);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product VALUES (1, 'apa borsec', 4, 1000);
INSERT INTO public.product VALUES (3, 'mere ', 3.5, 3299);
INSERT INTO public.product VALUES (2, 'coca-cola doza', 6, 1983);
INSERT INTO public.product VALUES (5, 'ram kingston', 3500, 121);


--
-- Data for Name: warehouse_order; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.warehouse_order VALUES (1, 2, 3, 13, '2026-05-21 10:12:50.972');
INSERT INTO public.warehouse_order VALUES (2, 1, 2, 17, '2026-05-21 10:13:22.593');


--
-- Name: bill_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bill_id_seq', 2, true);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 4, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 5, true);


--
-- Name: warehouse_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.warehouse_order_id_seq', 2, true);


--
-- Name: bill bill_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (id);


--
-- Name: client client_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_email_key UNIQUE (email);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: warehouse_order warehouse_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouse_order
    ADD CONSTRAINT warehouse_order_pkey PRIMARY KEY (id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


--
-- PostgreSQL database dump complete
--

