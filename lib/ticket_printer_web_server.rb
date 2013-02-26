#!/usr/bin/env jruby
require 'sinatra'
require 'sinatra/cross_origin'

require 'json'
require './lib/printer_lookup_service'
require './lib/ticket_printer_service'

module Sinatra
  register CrossOrigin
end

configure do
  enable :cross_origin
end

set :public_folder, 'public'


get '/' do
  'Hello - I am your ticket printer service!'
end

post '/print-tickets' do
  request.body.rewind  # in case someone already read it
  printer_command = JSON.parse request.body.read

  # Make this a command...
  printer_name = printer_command['printer'].to_i
  tickets = printer_command['tickets']
  page_format = printer_command['page_format']

  print_service = TicketPrinterService.new(tickets, page_format)
  print_service.print(printer_name)   # this is currently index... change to name

  201 # OK - Created (post success)
end

get '/printers' do
  JSON PrinterLookupService::lookup_printers
end

