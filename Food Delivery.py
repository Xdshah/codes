import random
final_order_details = {}
def kfc():
    cost_single = 0
    cost_deal = 0
    serial = 0
    print('\n\t***WELCOME TO KFC***')
    while True:
        while True:
            d_or_single = int(input('\nPress 1 for Deals. Press 2 for single order menu.\n\nWhat do you want to order? '))
            if d_or_single == 2:
                print('\n1. Mighty Zinger---Rs 500/-\n2. Zingaratha---Rs 250/-\n3. Chicken Nuggets---Rs 350/-')
                menu_single = {1: 'Mighty Zinger', 2: 'Zingratha', 3: 'Chicken Nuggets'}
                menu_prices = [0, 500, 250, 350]
                while True:
                    choice = int(input('\nEnter the choice: '))
                    if choice == 1:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice]*quantity) + '/-'})
                        break

                    elif choice == 2:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice]*quantity) + '/-'})
                        break

                    elif choice == 3:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice]*quantity) + '/-'})
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue
                break

            elif d_or_single == 1:
                print('\nDeal 1: 2 Mighty Zingers + 1 Zingaratha---Rs 1000/-\nDeal 2: 2 Mighty Zingers + 2 Drinks---Rs 850/-')
                menu_deal = {1: 'Deal # 1: 2 Mighty Zingers + 1 Zingaratha', 2: 'Deal # 2: 2 Mighty Zingers + 2 Drinks'}
                deals_prices = [0, 1000, 850]
                while True:
                    choice_deal = int(input('\nChoose Your deal: '))
                    if choice_deal == 1:
                        serial += 1
                        quantity_deal = int(input('\nHow many deals do you want? Enter pls: '))
                        cost_deal = cost_deal + quantity_deal * deals_prices[choice_deal]
                        final_order_details.update({(serial): str(quantity_deal) + '     ' + menu_deal[int(choice_deal)] + ' --- Rs ' + str(deals_prices[choice_deal] * quantity_deal) + '/-'})
                        break

                    elif choice_deal == 2:
                        serial += 1
                        quantity_deal = int(input('\nHow many deals do you want? Enter pls: '))
                        cost_deal = cost_deal + quantity_deal * deals_prices[choice_deal]
                        final_order_details.update({(serial): str(quantity_deal) + '     ' + menu_deal[int(choice_deal)] + ' --- Rs ' + str(deals_prices[choice_deal] * quantity_deal) + '/-'})
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue
                break

            else:
                print('\nInvalid choice. Please try Again')
                continue

        x = input('\nPress Y to order anything else.\nPress Enter to proceed to Checkout. ')
        x = x.upper()
        if x == 'Y':
            continue
        else:
            break


    global total_cost
    total_cost = cost_single + cost_deal
    return total_cost

def mcdonald():
    cost_single = 0
    cost_deal = 0
    serial = 0
    print('\n\t***WELCOME TO MCDONALDS***')
    while True:
        while True:
            d_or_single = int(input('\nPress 1 for Deals. Press 2 for single order menu.\n\nWhat do you want to order? '))
            if d_or_single == 2:
                print('\n1. Mccrispy---Rs 780/-\n2. Big Mac---Rs 560/-\n3. Fries---Rs 100/-')
                menu_single = {1: 'Mccrispy', 2: 'Big Mac', 3: 'Fries'}
                menu_prices = [0, 780, 560, 100]
                while True:
                    choice = int(input('\nEnter the choice: '))
                    if choice == 1:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice] * quantity) + '/-'})
                        break

                    elif choice == 2:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice] * quantity) + '/-'})
                        break

                    elif choice == 3:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice] * quantity) + '/-'})
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue
                break

            elif d_or_single == 1:
                print('\nDeal 1: 2 Mccrispy + 1 Big Mac---Rs 1500/-\nDeal 2: 1 Big Mac + 2 Fries---Rs 600/-')
                menu_deal = {1: 'Deal # 1: 2 Mccrispy + 1 Big Mac', 2: 'Deal # 2: 1 Big Mac + 2 Fries'}
                deals_prices = [0, 1500, 600]
                while True:
                    choice_deal = int(input('\nChoose Your deal: '))
                    if choice_deal == 1:
                        serial += 1
                        quantity_deal = int(input('\nHow many deals do you want? Enter pls: '))
                        cost_deal = cost_deal + quantity_deal * deals_prices[choice_deal]
                        final_order_details.update({(serial): str(quantity_deal) + '     ' + menu_deal[int(choice_deal)] + ' --- Rs ' + str(deals_prices[choice_deal] * quantity_deal) + '/-'})
                        break

                    elif choice_deal == 2:
                        serial += 1
                        quantity_deal = int(input('\nHow many deals do you want? Enter pls: '))
                        cost_deal = cost_deal + quantity_deal * deals_prices[choice_deal]
                        final_order_details.update({(serial): str(quantity_deal) + '     ' + menu_deal[int(choice_deal)] + ' --- Rs ' + str(deals_prices[choice_deal] * quantity_deal) + '/-'})
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue
                break

            else:
                print('\nInvalid choice. Please try Again')
                continue

        x = input('\nPress Y to order anything else.\nPress Enter to proceed to Checkout. ')
        x = x.upper()
        if x == 'Y':
            continue
        else:
            break

    global total_cost
    total_cost = cost_single + cost_deal
    return total_cost

def mamu_borgir_spot():
    cost_single = 0
    cost_deal = 0
    serial = 0
    print('\n\t***WELCOME TO MAMU BORGIR SPOT***')
    while True:
        while True:
            d_or_single = int(
                input('\nPress 1 for Deals. Press 2 for single order menu.\n\nWhat do you want to order? '))
            if d_or_single == 2:
                print('\n1. Anday Wala Borgir---Rs 120/-\n2. Anda Shami Borgir---Rs 150/-\n3. Chicken Borgir---Rs 200/-')
                menu_single = {1: 'Anday Wala Borgir', 2: 'Anda Shami Borgir', 3: 'Chicken Borgir'}
                menu_prices = [0, 120, 150, 200]
                while True:
                    choice = int(input('\nEnter the choice: '))
                    if choice == 1:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice] * quantity) + '/-'})
                        break

                    elif choice == 2:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice] * quantity) + '/-'})
                        break

                    elif choice == 3:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice] * quantity) + '/-'})
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue
                break

            elif d_or_single == 1:
                print('\nDeal 1: 2 Anday Wala Borgir + 2 Chicken Borgir---Rs 300/-\nDeal 2: 1 Anday Wala Borgir + 1 Anda Shami Borgir + 1 Chicken Borgir---Rs 400/-')
                menu_deal = {1: 'Deal # 1: 2 Anday Wala Borgir + 2 Chicken Borgir', 2: 'Deal # 2: 1 Anday Wala Borgir + 1 Anda Shami Borgir + 1 Chicken Borgir'}
                deals_prices = [0, 300, 400]
                while True:
                    choice_deal = int(input('\nChoose Your deal: '))
                    if choice_deal == 1:
                        serial += 1
                        quantity_deal = int(input('\nHow many deals do you want? Enter pls: '))
                        cost_deal = cost_deal + quantity_deal * deals_prices[choice_deal]
                        final_order_details.update({(serial): str(quantity_deal) + '     ' + menu_deal[int(choice_deal)] + ' --- Rs ' + str(deals_prices[choice_deal] * quantity_deal) + '/-'})
                        break

                    elif choice_deal == 2:
                        serial += 1
                        quantity_deal = int(input('\nHow many deals do you want? Enter pls: '))
                        cost_deal = cost_deal + quantity_deal * deals_prices[choice_deal]
                        final_order_details.update({(serial): str(quantity_deal) + '     ' + menu_deal[int(choice_deal)] + ' --- Rs ' + str(deals_prices[choice_deal] * quantity_deal) + '/-'})
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue
                break

            else:
                print('\nInvalid choice. Please try Again')
                continue

        x = input('\nPress Y to order anything else.\nPress Enter to proceed to Checkout. ')
        x = x.upper()
        if x == 'Y':
            continue
        else:
            break

    global total_cost
    total_cost = cost_single + cost_deal
    return total_cost

def hassan_shawarma():
    cost_single = 0
    cost_deal = 0
    serial = 0
    print('\n\t***WELCOME TO HASSAN SHAWARMA***')
    while True:
        while True:
            d_or_single = int(
                input('\nPress 1 for Deals. Press 2 for single order menu.\n\nWhat do you want to order? '))
            if d_or_single == 2:
                print('\n1. Chicken Shawarma---Rs 60/-\n2. Charsi Shawarma---Rs 120/-')
                menu_single = {1: 'Chicken Shawarma', 2: 'Charsi Shawarma'}
                menu_prices = [0, 60, 120]
                while True:
                    choice = int(input('\nEnter the choice: '))
                    if choice == 1:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice] * quantity) + '/-'})
                        break

                    elif choice == 2:
                        serial += 1
                        quantity = int(input('\nEnter your Quantity: '))
                        cost_single = cost_single + quantity * menu_prices[choice]
                        final_order_details.update({(serial): str(quantity) + '     ' + menu_single[int(choice)] + ' --- Rs ' + str(menu_prices[choice] * quantity) + '/-'})
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue
                break

            elif d_or_single == 1:
                print('\nDeal 1: 2 Chicken Shawarma + 2 Charsi Shawarma---Rs 200/-\nDeal 2: 3 Chicken Shawaram + 3 Charsi Shawarma---Rs 400/-')
                menu_deal = {1: 'Deal # 1: 2 Chicken Shawarma + 2 Charsi Shawarma', 2: 'Deal # 2: 3 Chicken Shawaram + 3 Charsi Shawarma'}
                deals_prices = [0, 200, 400]
                while True:
                    choice_deal = int(input('\nChoose Your deal: '))
                    if choice_deal == 1:
                        serial += 1
                        quantity_deal = int(input('\nHow many deals do you want? Enter pls: '))
                        cost_deal = cost_deal + quantity_deal * deals_prices[choice_deal]
                        final_order_details.update({(serial): str(quantity_deal) + '     ' + menu_deal[int(choice_deal)] + ' --- Rs ' + str(deals_prices[choice_deal] * quantity_deal) + '/-'})
                        break

                    elif choice_deal == 2:
                        serial += 1
                        quantity_deal = int(input('\nHow many deals do you want? Enter pls: '))
                        cost_deal = cost_deal + quantity_deal * deals_prices[choice_deal]
                        final_order_details.update({(serial): str(quantity_deal) + '     ' + menu_deal[int(choice_deal)] + ' --- Rs ' + str(deals_prices[choice_deal] * quantity_deal) + '/-'})
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue
                break

            else:
                print('\nInvalid choice. Please try Again')
                continue

        x = input('\nPress Y to order anything else.\nPress Enter to proceed to Checkout. ')
        x = x.upper()
        if x == 'Y':
            continue
        else:
            break

    global total_cost
    total_cost = cost_single + cost_deal
    return total_cost

print('\n\t**********WELCOME TO FAZEELA, FARINA and SARAM FOODS LTD**********\n')
print('''\t\t\t     **********      **********      **********
                 **********      **********      **********
                 **              **              **
                 **              **              **
                 **********      **********      **********
                 **********      **********      **********
                 **              **                      **
                 **              **                      **
                 **              **              **********
                 **              **              **********''')

print('\nOur service is available in following areas:\n1. DHA\n2. I-8\n3. Hostel City\n4. Nala Lai')
while True:
    choice = input('\nIf your location is listed, press Y, else press N: ')

    if choice.upper() == 'Y':

        while True:
            location = int(input('\nChoose your location: '))

            if location == 1:

                l_receipt = 'DHA'
                while True:
                    print('\nFollowing Restaurants are available in DHA.\n1. KFC\n2. McDonald\n3. Mamu Borgir Spot\n4. Hassan Shawarma\n5. Comsats university')
                    rest_selection = int(input('\nChoose your restaurant: '))

                    if rest_selection == 1:
                        rest_receipt = 'KFC'
                        kfc()
                        break

                    elif rest_selection == 2:
                        rest_receipt = 'McDonalds'
                        mcdonald()
                        break

                    elif rest_selection == 3:
                        rest_receipt = 'Mamu Borgir Spot'
                        mamu_borgir_spot()
                        break

                    elif rest_selection == 4:
                        rest_receipt = 'Hassan Shawarma'
                        hassan_shawarma()
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue

                break

            elif location == 2:
                l_receipt = 'I-8'
                while True:
                    print('\nFollowing Restaurants are available in I-8.\n1. KFC\n2. McDonald\n3. Mamu Borgir Spot\n4. Hassan Shawarma')
                    rest_selection = int(input('\nChoose your restaurant: '))

                    if rest_selection == 1:
                        rest_receipt = 'KFC'
                        kfc()
                        break

                    elif rest_selection == 2:
                        rest_receipt = 'McDonalds'
                        mcdonald()
                        break

                    elif rest_selection == 3:
                        rest_receipt = 'Mamu Borgir Spot'
                        mamu_borgir_spot()
                        break

                    elif rest_selection == 4:
                        rest_receipt = 'Hassan Shawarma'
                        hassan_shawarma()
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue

                break

            elif location == 3:
                l_receipt = 'Hostel City'
                while True:
                    print('\nFollowing Restaurants are available in Hostel City.\n1. KFC\n2. McDonald\n3. Mamu Borgir Spot\n4. Hassan Shawarma')
                    rest_selection = int(input('\nChoose your restaurant: '))

                    if rest_selection == 1:
                        rest_receipt = 'KFC'
                        kfc()
                        break

                    elif rest_selection == 2:
                        rest_receipt = 'McDonalds'
                        mcdonald()
                        break

                    elif rest_selection == 3:
                        rest_receipt = 'Mamu Borgir Spot'
                        mamu_borgir_spot()
                        break

                    elif rest_selection == 4:
                        rest_receipt = 'Hassan Shawarma'
                        hassan_shawarma()
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue

                break

            elif location == 4:
                l_receipt = 'Nala Lai'
                while True:
                    print('\nFollowing Restaurants are available in Nala Lai.\n1. KFC\n2. McDonald\n3. Mamu Borgir Spot\n4. Hassan Shawarma')
                    rest_selection = int(input('\nChoose your restaurant: '))

                    if rest_selection == 1:
                        rest_receipt = 'KFC'
                        kfc()
                        break

                    elif rest_selection == 2:
                        rest_receipt = 'McDonalds'
                        mcdonald()
                        break

                    elif rest_selection == 3:
                        rest_receipt = 'Mamu Borgir Spot'
                        mamu_borgir_spot()
                        break

                    elif rest_selection == 4:
                        rest_receipt = 'Hassan Shawarma'
                        hassan_shawarma()
                        break

                    else:
                        print('\nInvalid Choice. Please Try Again.')
                        continue

                break

            else:
                print('\nInvalid Choice. Please Try Again.')
                continue

        print('\n**********COSTUMER DETAILS**********\n\nEnter your details.')
        name = input('\nEnter your name: ')
        phone = input('Enter your phone number: ')
        address = input(('Enter your address: '))
        payment = int(input('\n**********PAYMENT**********\n\nHow do you want to pay?\n1. Cash on delivery\n2. Debit Card\nChoose 1 or 2: '))

        if payment == 2:
            payment_choice = 'Through debit card'
            status = 'Paid'
            cc_no = int(input('\nEnter your credit card number: '))
            expiry = (input('Enter your credit card\'s expiry date: '))
            cvv = int(input('Enter your Credit Card\'s CVV: '))

        elif payment == 1:
            payment_choice = 'Cash on Delivery'
            status = 'Not Paid'
        print('\nYour order is placed. Here is your reciept')
        order_id = random.randint(1000, 10000)
        print(f'''\n*********** ORDER RECEIPT ***********\n\nOrder ID: {order_id}\nCostumer Name: {name}\nCostumer Phone Number: {phone}\nCostumer Address: {address}\nLocation: {l_receipt}\nRestaurant: {rest_receipt}\n''')
        print('Order Details:')
        print('Serial   QTY    Detail')
        for key, value in final_order_details.items():
            print(' ', key, '     ', value)
        print(f'\nTotal Bill: Rs {total_cost}\nPayment Method: {payment_choice}\nPayment Status: {status}\n\nThanks for shopping with us. Your order will reach you soon.')
        break

    elif choice.upper() == 'N':
        print('\nSorry for inconvenience. We will expand our operations to your area soon.')
        break

    else:
        print('\nInvalid Choice. Please Try Again.')
        continue