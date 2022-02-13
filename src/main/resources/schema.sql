CREATE TABLE IF NOT EXISTS "public"."blog_comment" (
     "id"               integer NOT NULL,
     "author"           character varying(255),
     "comment_id"       integer,
     "comment_time"     "date",
     "content"          "text",
     "main_parent_id"   integer,
     "blog_id"          integer
);

CREATE SEQUENCE IF NOT EXISTS "public"."blog_comment_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS "public"."blog_post" (
      "id"                  integer NOT NULL,
      "author"              character varying(255),
      "category"            character varying(255),
      "content"             "text",
      "description"         character varying(255),
      "picture_description" character varying(255),
      "picture_size"        character varying(255),
      "picture_url"         "text",
      "post_time"           "date",
      "tags"                character varying(255),
      "title"               character varying(255)
);

CREATE SEQUENCE IF NOT EXISTS "public"."blog_post_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
