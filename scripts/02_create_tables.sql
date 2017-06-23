-- Table: public.ccpayments

-- DROP TABLE public.ccpayments;

CREATE TABLE public.ccpayments
(
    customer_id integer NOT NULL,
    carddetails_cardno bigint NOT NULL,
    savingsdetails_accountno integer NOT NULL,
    transferred_amount double precision NOT NULL,
    current_trans_date text COLLATE pg_catalog."default" NOT NULL,
    transaction_id character varying COLLATE pg_catalog."default" NOT NULL,
    transaction_status character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "CCPayments_pkey" PRIMARY KEY (transaction_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.ccpayments
    OWNER to postgres;