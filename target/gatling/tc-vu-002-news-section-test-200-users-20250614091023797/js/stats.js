var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "200",
        "ok": "196",
        "ko": "4"
    },
    "minResponseTime": {
        "total": "337",
        "ok": "337",
        "ko": "3862"
    },
    "maxResponseTime": {
        "total": "5113",
        "ok": "2939",
        "ko": "5113"
    },
    "meanResponseTime": {
        "total": "942",
        "ok": "874",
        "ko": "4280"
    },
    "standardDeviation": {
        "total": "665",
        "ok": "462",
        "ko": "491"
    },
    "percentiles1": {
        "total": "766",
        "ok": "758",
        "ko": "4143"
    },
    "percentiles2": {
        "total": "1028",
        "ok": "1006",
        "ko": "5113"
    },
    "percentiles3": {
        "total": "2027",
        "ok": "1890",
        "ko": "5113"
    },
    "percentiles4": {
        "total": "4143",
        "ok": "2823",
        "ko": "5113"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 110,
    "percentage": 55.00000000000001
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 51,
    "percentage": 25.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 35,
    "percentage": 17.5
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 4,
    "percentage": 2.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.65",
        "ok": "1.62",
        "ko": "0.03"
    }
},
contents: {
"req_access-news-sec-629658548": {
        type: "REQUEST",
        name: "Access News Section",
path: "Access News Section",
pathFormatted: "req_access-news-sec-629658548",
stats: {
    "name": "Access News Section",
    "numberOfRequests": {
        "total": "200",
        "ok": "196",
        "ko": "4"
    },
    "minResponseTime": {
        "total": "337",
        "ok": "337",
        "ko": "3862"
    },
    "maxResponseTime": {
        "total": "5113",
        "ok": "2939",
        "ko": "5113"
    },
    "meanResponseTime": {
        "total": "942",
        "ok": "874",
        "ko": "4280"
    },
    "standardDeviation": {
        "total": "665",
        "ok": "462",
        "ko": "491"
    },
    "percentiles1": {
        "total": "766",
        "ok": "758",
        "ko": "4143"
    },
    "percentiles2": {
        "total": "1028",
        "ok": "1006",
        "ko": "5113"
    },
    "percentiles3": {
        "total": "2027",
        "ok": "1890",
        "ko": "5113"
    },
    "percentiles4": {
        "total": "4143",
        "ok": "2823",
        "ko": "5113"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 110,
    "percentage": 55.00000000000001
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 51,
    "percentage": 25.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 35,
    "percentage": 17.5
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 4,
    "percentage": 2.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.65",
        "ok": "1.62",
        "ko": "0.03"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
