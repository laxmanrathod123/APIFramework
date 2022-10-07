Feature: Validating addd place API's

  @AddPlaceAP
  Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
    Given Add place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call got succss with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id craeted maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name         | language | address         |
      | Rathod Lucky | English  | Pune Maharastra |

  #  | Rathod Priyanka | Marathi  | Mumbai Maharastra |
  # every scenario in test case is treated like one test case
  @DeletePlaceAPI
  Scenario: Verify if the delete Place functionlity is working or not
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call got succss with status code 200
    And "status" in response body is "OK"
