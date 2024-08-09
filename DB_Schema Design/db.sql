TABLE Assessment
{
  id varchar
  setname varchar [primary key]
  created_by varchar
  domain varchar
  status varchar
   

}
TABLE Question
{
  question_id varchar [primary key]
  setname varchar
  domain varchar 
  question    string

}
TABLE Option
{
  option_id varchar [primary key]
  question_id varchar 
  option varchar 
  suggestion varchar

}
TABLE Survey
{
  survey_id varchar [primary key] 
  requestor varchar 
  company_name varchar
  setname varchar
  domain varchar
  status varchar
}

TABLE Login 
{
    username varchar [primary key]
    password VARCHAR(255) [not null]

}
Ref: "Assessment"."setname" < "Question"."setname"

Ref: "Question"."question_id" < "Option"."question_id"

Ref: "Login"."username" < "Assessment"."created_by"
Ref: "Login"."username" < "Survey"."requestor"
