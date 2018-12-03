# Cache Service Rest APIs

**Add to cache**
----
  Returns boolean response.

* **URL:**
  /v1/cache/add

* **Method:**
  `POST`

* **Body**

   **Content:** `{ "key" : "country"", "value" : "capital" }`
   
* **Success Response:**

  * **Code:** 201 <br />
    **Content:** `{ true }`
    
 * **Sample Call:**
   
     ```
     curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
        "key": "country", \ 
        "value": "capital" \ 
      }' 'https://cache-service.herokuapp.com/v1/cache/add'
     ```
 
 **Get from cache**
 ----
   Returns element from cache for a given key.
 
 * **URL:**
 /v1/cache/get/{key}
 
 * **Method:**
    `GET`
   
 *  **URL Params**
 
    **Required:**
  `key=[string]`
 
 * **Success Response:**
 
   * **Code:** 200 <br />
     **Content:** `{ "Value" }`
  
 * **Error Response:**
 
   * **Code:** 204 No Content <br />
     **Content:** `{ no content }`
     
 * **Sample Call:**
   
     ```
       curl -X GET --header 'Accept: application/json' 
       'https://cache-service.herokuapp.com/v1/cache/get/country'
     ```

**Peek from cache**
 ----
   Returns most recently added element from cache.
 
 * **URL:**
 /v1/cache/peek
 
 * **Method:**
    `GET`
   
 *  **URL Params**
 
    **Required:**
         None
 
 * **Success Response:**
 
   * **Code:** 200 <br />
     **Content:** `{ "Value" }`
  
 * **Error Response:**
 
   * **Code:** 204 No Content <br />
     **Content:** `{ no content }`
* **Sample Call:**
   
     ```
      curl -X GET --header 'Accept: application/json' 
       'https://cache-service.herokuapp.com/v1/cache/peek'
     
     ```
**Take from cache**
 ----
   Retrieves and removes the most recently added element from the
   cache.
 
 * **URL:**
 /v1/cache/take
 
 * **Method:**
    `GET`
   
 *  **URL Params**
 
    **Required:**
         None
 
 * **Success Response:**
 
   * **Code:** 200 <br />
     **Content:** `{ "Value" }`
  
 * **Error Response:**
 
   * **Code:** 204 No Content <br />
     **Content:** `{ no content }`
* **Sample Call:**
   
     ```
      curl -X GET --header 'Accept: application/json' 
       'https://cache-service.herokuapp.com/v1/cache/take'
     ```
**Remove from cache**
 ----
   Returns true if the element was successfully removed.
 
 * **URL:**
  /v1/cache/remove/{key} 
 
 * **Method:**
    `DELETE`
   
 *  **URL Params**
 
    **Required:**
  `key=[string]`
 
 * **Success Response:**
 
   * **Code:** 200 <br />
     **Content:** `{ true }`
  
 * **Sample Call:**
   
     ```
       curl -X DELETE --header 'Accept: application/json' 
        'https://cache-service.herokuapp.com/v1/cache/remove/country'
     ```