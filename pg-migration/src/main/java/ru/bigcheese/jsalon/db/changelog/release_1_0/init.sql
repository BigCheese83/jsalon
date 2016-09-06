--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE address (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    country character varying NOT NULL,
    district character varying,
    city character varying NOT NULL,
    street character varying NOT NULL,
    house character varying NOT NULL,
    section character varying,
    flat character varying NOT NULL,
    zip character varying
);


ALTER TABLE address OWNER TO postgres;

--
-- Name: address_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE address_id_seq OWNER TO postgres;

--
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE address_id_seq OWNED BY address.id;


--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE client (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    person_id bigint NOT NULL,
    reg_date date NOT NULL,
    discount_id bigint,
    in_blacklist boolean DEFAULT false NOT NULL
);


ALTER TABLE client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE client_id_seq OWNED BY client.id;


--
-- Name: contact; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contact (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    phone character varying NOT NULL,
    email character varying,
    vk character varying,
    skype character varying,
    facebook character varying,
    twitter character varying,
    icq character varying
);


ALTER TABLE contact OWNER TO postgres;

--
-- Name: contact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contact_id_seq OWNER TO postgres;

--
-- Name: contact_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contact_id_seq OWNED BY contact.id;


--
-- Name: discount; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE discount (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    name character varying NOT NULL,
    value integer NOT NULL,
    description character varying
);


ALTER TABLE discount OWNER TO postgres;

--
-- Name: discount_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE discount_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE discount_id_seq OWNER TO postgres;

--
-- Name: discount_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE discount_id_seq OWNED BY discount.id;


--
-- Name: master; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE master (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    person_id bigint NOT NULL,
    hiring_date date,
    post_id bigint NOT NULL
);


ALTER TABLE master OWNER TO postgres;

--
-- Name: master_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE master_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE master_id_seq OWNER TO postgres;

--
-- Name: master_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE master_id_seq OWNED BY master.id;


--
-- Name: passport; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE passport (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    series character varying DEFAULT ''::character varying NOT NULL,
    num character varying NOT NULL,
    issued_by character varying NOT NULL,
    issue_date date NOT NULL,
    subdivision character varying,
    country character varying NOT NULL
);


ALTER TABLE passport OWNER TO postgres;

--
-- Name: passport_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE passport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE passport_id_seq OWNER TO postgres;

--
-- Name: passport_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE passport_id_seq OWNED BY passport.id;


--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE person (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    surname character varying NOT NULL,
    name character varying NOT NULL,
    patronymic character varying,
    birth_date date,
    passport_id bigint,
    reg_address_id bigint,
    live_address_id bigint,
    contact_id bigint NOT NULL
);


ALTER TABLE person OWNER TO postgres;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_id_seq OWNER TO postgres;

--
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE person_id_seq OWNED BY person.id;


--
-- Name: post; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE post (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    name character varying NOT NULL,
    description character varying
);


ALTER TABLE post OWNER TO postgres;

--
-- Name: post_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE post_id_seq OWNER TO postgres;

--
-- Name: post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE post_id_seq OWNED BY post.id;


--
-- Name: post_service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE post_service (
    id bigint NOT NULL,
    post_id bigint NOT NULL,
    service_id bigint NOT NULL
);


ALTER TABLE post_service OWNER TO postgres;

--
-- Name: post_service_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE post_service_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE post_service_id_seq OWNER TO postgres;

--
-- Name: post_service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE post_service_id_seq OWNED BY post_service.id;


--
-- Name: schedule; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE schedule (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    reg_date date NOT NULL,
    reg_time time without time zone NOT NULL,
    surname character varying NOT NULL,
    name character varying NOT NULL,
    phone character varying NOT NULL,
    master_id bigint,
    service_id bigint NOT NULL,
    status character varying NOT NULL,
    note character varying
);


ALTER TABLE schedule OWNER TO postgres;

--
-- Name: schedule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE schedule_id_seq OWNER TO postgres;

--
-- Name: schedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE schedule_id_seq OWNED BY schedule.id;


--
-- Name: service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE service (
    id bigint NOT NULL,
    created timestamp without time zone,
    modified timestamp without time zone,
    version integer NOT NULL,
    name character varying NOT NULL,
    cost numeric NOT NULL,
    duration integer NOT NULL,
    description character varying
);


ALTER TABLE service OWNER TO postgres;

--
-- Name: service_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE service_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE service_id_seq OWNER TO postgres;

--
-- Name: service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE service_id_seq OWNED BY service.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY address ALTER COLUMN id SET DEFAULT nextval('address_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contact ALTER COLUMN id SET DEFAULT nextval('contact_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY discount ALTER COLUMN id SET DEFAULT nextval('discount_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY master ALTER COLUMN id SET DEFAULT nextval('master_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY passport ALTER COLUMN id SET DEFAULT nextval('passport_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person ALTER COLUMN id SET DEFAULT nextval('person_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY post ALTER COLUMN id SET DEFAULT nextval('post_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY post_service ALTER COLUMN id SET DEFAULT nextval('post_service_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule ALTER COLUMN id SET DEFAULT nextval('schedule_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service ALTER COLUMN id SET DEFAULT nextval('service_id_seq'::regclass);


--
-- Name: address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: contact_phone_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contact
    ADD CONSTRAINT contact_phone_key UNIQUE (phone);


--
-- Name: contact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (id);


--
-- Name: discount_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY discount
    ADD CONSTRAINT discount_name_key UNIQUE (name);


--
-- Name: discount_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY discount
    ADD CONSTRAINT discount_pkey PRIMARY KEY (id);


--
-- Name: master_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY master
    ADD CONSTRAINT master_pkey PRIMARY KEY (id);


--
-- Name: passport_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY passport
    ADD CONSTRAINT passport_pkey PRIMARY KEY (id);


--
-- Name: passport_series_num_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY passport
    ADD CONSTRAINT passport_series_num_key UNIQUE (series, num);


--
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: person_surname_name_contact_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_surname_name_contact_id_key UNIQUE (surname, name, contact_id);


--
-- Name: post_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY post
    ADD CONSTRAINT post_name_key UNIQUE (name);


--
-- Name: post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);


--
-- Name: post_service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY post_service
    ADD CONSTRAINT post_service_pkey PRIMARY KEY (id);


--
-- Name: post_service_post_id_service_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY post_service
    ADD CONSTRAINT post_service_post_id_service_id_key UNIQUE (post_id, service_id);


--
-- Name: schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (id);


--
-- Name: service_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY service
    ADD CONSTRAINT service_name_key UNIQUE (name);


--
-- Name: service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- Name: client_discount_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_discount_id_fkey FOREIGN KEY (discount_id) REFERENCES discount(id);


--
-- Name: client_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_person_id_fkey FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: master_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY master
    ADD CONSTRAINT master_person_id_fkey FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: master_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY master
    ADD CONSTRAINT master_post_id_fkey FOREIGN KEY (post_id) REFERENCES post(id);


--
-- Name: person_contact_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_contact_id_fkey FOREIGN KEY (contact_id) REFERENCES contact(id);


--
-- Name: person_live_address_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_live_address_id_fkey FOREIGN KEY (live_address_id) REFERENCES address(id);


--
-- Name: person_passport_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_passport_id_fkey FOREIGN KEY (passport_id) REFERENCES passport(id);


--
-- Name: person_reg_address_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_reg_address_id_fkey FOREIGN KEY (reg_address_id) REFERENCES address(id);


--
-- Name: post_service_post_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY post_service
    ADD CONSTRAINT post_service_post_id_fkey FOREIGN KEY (post_id) REFERENCES post(id);


--
-- Name: post_service_service_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY post_service
    ADD CONSTRAINT post_service_service_id_fkey FOREIGN KEY (service_id) REFERENCES service(id);


--
-- Name: schedule_master_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_master_id_fkey FOREIGN KEY (master_id) REFERENCES person(id);


--
-- Name: schedule_service_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_service_id_fkey FOREIGN KEY (service_id) REFERENCES service(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "postgres";
GRANT ALL ON SCHEMA public TO "postgres";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

